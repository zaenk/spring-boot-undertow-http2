var connection = "";
if ( performance && performance.timing.nextHopProtocol ) {
    connection = performance.timing.nextHopProtocol;
} else if ( window.chrome && window.chrome.loadTimes ) {
    connection = window.chrome.loadTimes().connectionInfo;
} else {
    connection = "Browser does not expose connection protocol";
}
console.log('Protocol:' + connection);
document.getElementById("inbound").innerHTML = connection;
