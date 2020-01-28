'use strict'
var app = require('express')();
var http = require('http').Server(app);
const mongo = require('mongodb').MongoClient;
const port = 3000;
import {TimeManagement} from "./TimeManagement";
const url = "mongodb://Test:Test@managementcluster-shard-00-00-ydyhm.mongodb.net:27017,managementcluster-shard-00-01-ydyhm.mongodb.net:27017,managementcluster-shard-00-02-ydyhm.mongodb.net:27017/test?ssl=true&replicaSet=ManagementCluster-shard-0&authSource=admin&retryWrites=true&w=majority";
//const client = new MongoClient(uri, {useNewUrlParser: true});
var db;
mongo.connect(url, {
    useNewUrlParser:true,
    useUnifiedTopology:true
},(err, client)=>{
    db = client.db('GroupProject').collection('UserData');
    if(err){
        console.error(err);
        return
    }
})

var userdata = {
    "username":"",
    "password":"",
    "DOB": "",
    "TimeZone": "",
};
app.get('/', function(req, res){
	console.log("Requested")
    db.insertOne(userdata)
        .then(result => console.log(result.insertedId))
        .catch(err => console.error(err))
    
});
app.get('/update', function(req, res){
    res.send('update works');
});

app.get('/joinSlot', (req:JSON, res:any)=>{
    
})



//Have a click based system or add new database
app.get('/joinSession')

app.listen(port, function(){
    console.log("listening on *: 3000");
});