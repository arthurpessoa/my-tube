/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubermiserver.mongo;

import java.io.InputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 *
 * @author Arthur
 */
public class GridFileSystem {

    private final String hostAddress;
    private final int portAddress;
    
    public GridFileSystem(String hostname, int port) {
        hostAddress = hostname;
        portAddress = port;
    }

    public void sendVideo(String fileName, InputStream videoStream) throws UnknownHostException, MongoException, IOException  {
        Mongo mongo = new Mongo(hostAddress, portAddress);
        
        DB db = mongo.getDB("MyTube");
        
        // create a "video" namespace
        GridFS gfsPhoto = new GridFS(db, "video");
        
        //get the file from stream
        GridFSInputFile gfsFile = gfsPhoto.createFile(videoStream);
        
        // set a new filename for identify purpose
	gfsFile.setFilename(fileName);
 
        // save the image file into mongoDB
        gfsFile.save();
        videoStream.close();
    }
    
    

    public InputStream receiveVideo(String fileName) throws UnknownHostException, MongoException, IOException   {
        Mongo mongo = new Mongo(hostAddress, portAddress);        
        DB db = mongo.getDB("MyTube");
        
        // create a "video" namespace
        GridFS gfsPhoto = new GridFS(db, "video");
        
        // get image file by it's filename
	GridFSDBFile videoForOutput = gfsPhoto.findOne(fileName);        
        return videoForOutput.getInputStream();
                
    }
        
    public void RemoveVideo(String fileName) throws UnknownHostException, MongoException, IOException  {
        Mongo mongo = new Mongo(hostAddress, portAddress);
        
        DB db = mongo.getDB("MyTube");        
        // create a "video" namespace
        GridFS gfsVideo = new GridFS(db, "video");
        // remove the image file from mongoDB
        gfsVideo.remove(fileName);        
    }
}

    