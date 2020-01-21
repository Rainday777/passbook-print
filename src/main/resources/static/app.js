var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket =  new SockJS('/passbook/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        
        stompClient.subscribe('/topic/find/success', function (printer) {
        	showPrinter(JSON.parse(printer.body).printerName);
        });
        
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendPrinter() {
    stompClient.send("/app/setup", {}, JSON.stringify({'printerName': $("#printerName").val()}));
}

function testPrint() {
	var lines = new Array();
	for (var i = 0; i < 24; i++) {

        lines[i] = "Line Line Line Line Line Line Line Line Line " + i;

    }
    stompClient.send("/app/print", {}, 
    		JSON.stringify(
    		{'copies': 1,
    		 'header1':'',
    		 'header2':'',
    		 'branch':'แจ้งวัฒนะ' ,
    		 'accountNo':'1234-4567-90',
    		 'holderName':'นายรักประเทศไทย รักชาตื',
    		 'holderAddress1':'123/456 ข 789',
    		 'holderAddress2':'thailand',
    		 'holderAddress3':'Bangkok',
    		 'printCoverPage':'false',
    		 'lines':lines,
    		 'paperSize':{
    			 'width':'5.0in', 
    			 'height':'7.0in',
    			 'autoResize':'true'
    		 	}
    		}
    		));
}

function findPrinter() {
    stompClient.send("/app/find", {},{});
}

function showGreeting(message) {
    $("#message").append("<tr><td>" + message + "</td></tr>");
}

function showPrinter(message) {
    $("#printer").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#sendPrinter" ).click(function() { sendPrinter(); });
    $( "#findPrinter" ).click(function() { findPrinter(); });
    $( "#testPrint" ).click(function() { testPrint(); });
});