import cgi
import urllib

from google.appengine.api import users
from google.appengine.ext import ndb

import webapp2


class Video(ndb.Model):
	#videoid = ndb.KeyProperty(indexed=True, required=True)
	desc = ndb.StringProperty(indexed=False)    
	date = ndb.DateTimeProperty(auto_now_add=True)


class MainPage(webapp2.RequestHandler):

    def get(self):
    	self.response.write("")
        


class Newvideo(webapp2.RequestHandler):

    def post(self):
        video = Video(id=self.request.get('videoid'))
        video.desc = self.request.get('desc')
        
        v = video.put()        
        print "AEpost: ", self.request.get('videoid'),'put: ', v.id(), v.kind()
        self.redirect('/')

class Getvideo(webapp2.RequestHandler):

    def get(self):
        videoid = self.request.get('videoid')
        print "AE: ", videoid
        
       	video = (ndb.Key(Video, videoid)).get();
       	if video is not None:
       		self.response.headers.add('desc', str(video.desc))
       		self.response.headers.add('date', str(video.date))

        	self.response.write("")
        else:
       		self.response.write("video not found :(")


application = webapp2.WSGIApplication([
    ('/', MainPage),
    ('/newvideo', Newvideo),
    ('/getvideo',Getvideo),
], debug=True)