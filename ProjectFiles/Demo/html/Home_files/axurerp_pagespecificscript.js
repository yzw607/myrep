for(var i = 0; i < 13; i++) { var scriptId = 'u' + i; window[scriptId] = document.getElementById(scriptId); }

$axure.eventManager.pageLoad(
function (e) {

});
gv_vAlignTable['u3'] = 'top';gv_vAlignTable['u12'] = 'top';
u5.style.cursor = 'pointer';
$axure.eventManager.click('u5', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('首页.html');

}
});
gv_vAlignTable['u1'] = 'center';gv_vAlignTable['u9'] = 'center';
u6.style.cursor = 'pointer';
$axure.eventManager.click('u6', function(e) {

if (true) {

	parent.window.close();

}
});
gv_vAlignTable['u2'] = 'top';gv_vAlignTable['u11'] = 'center';