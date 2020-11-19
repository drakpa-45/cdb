/**
 * Author: Dechen Wangdi
 * Created on: 1/14/2017.
 */

//This is to load the js based on specified URL.
var scriptLoader = ( function (url) {
    url = '/resources/js/'+url+'.js';
    $.ajax({
        type: 'GET',
        url: url,
        dataType: "script",
        cache: false
    });
});

//To load all the js based on URL match.
$(document).ready(function () {
    if (document.URL.search("engineer") > 1) {
        scriptLoader("cdb/engineer");
    }
});