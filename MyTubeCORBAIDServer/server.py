#!/usr/bin/env python

import sys,hashlib, datetime
from omniORB import CORBA, PortableServer
import ProjSD, ProjSD__POA
from pymongo import Connection
from pymongo import MongoClient
import CosNaming

class server_i(ProjSD__POA.IdServer):
	def novaChave(self):
		con = MongoClient()
		filme = con.mytube.filme
		codigo = str(datetime.datetime.now().time())
		codigo.replace(" ", "")
		filme.insert({'nome':'UP','chave':codigo});
		print codigo
		return codigo
	def verificaChave(self,chave):
		con = MongoClient()
		filme = con.mytube.filme
		result = filme.find({'chave':chave})
		for lol in result:
			print lol
		if result.count() == 0:
			print "Nao Achei"
			return False
		else:
			print "achei"
			return True

orb = CORBA.ORB_init(sys.argv,CORBA.ORB_ID)
poa = orb.resolve_initial_references("RootPOA")

ei = server_i()
eo = ei._this()

obj = orb.resolve_initial_references("NameService")
rootContext = obj._narrow(CosNaming.NamingContext)

if rootContext is None:
	print "Failed to narrow the root naming context"
	sys.exit(1)

name = [CosNaming.NameComponent("test","my_context")]
try:
	testContext = rootContext.bind_new_context(name)
	print "new test context bound"

except CosNaming.NamingContext.AlreadyBound,ex:
	print "Teste context already exists"
	obj = rootContext.resolve(name)
	testContext = obj._narrow(CosNaming.NamingContext)
	if testContext is None:
		print "test.mycontext exists but is not a Naming Context"
		sys.exit(1)

name = [CosNaming.NameComponent("mytube","Object")]
try:
	testContext.bind(name,eo)
	print "New ExampleEcho object bound"

except CosNaming.NamingContext.AlreadyBound:
	testContext.rebind(name,eo)
	print "ExampleEcho binding already existed --rebound"

poaManager = poa._get_the_POAManager()
poaManager.activate()

orb.run()
