var mongoose = require('mongoose');

// Database URL
let mongoDB = 'mongodb://localhost:27017/diary';

// Connection to the database
mongoose.Promise = global.Promise;
try {
    connection = mongoose.connect(mongoDB, {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        checkServerIdentity: false,
        useCreateIndex:true
    });
    console.log('connection to mongodb worked!');
} catch (e) {
    console.log('error in db connection: ' + e.message);
}