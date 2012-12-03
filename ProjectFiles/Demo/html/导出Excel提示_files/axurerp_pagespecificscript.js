for(var i = 0; i < 3; i++) { var scriptId = 'u' + i; window[scriptId] = document.getElementById(scriptId); }

$axure.eventManager.pageLoad(
function (e) {

});

u0.style.cursor = 'pointer';
$axure.eventManager.click('u0', function(e) {

if (true) {

	parent.window.close();

}
});
gv_vAlignTable['u2'] = 'center';