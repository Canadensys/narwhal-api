<#assign page={"title":"404"}>
<#include "../inc/header.ftl">
<div id="feedback_bar"><a href="https://github.com/Canadensys/narwhal-api/issues/new" target="_blank" title="${rc.getMessage("tools.common.feedback.hover")}">&nbsp;</a></div>
<#include "../inc/canadensys-header.ftl">
<div id="body">
	<div id="content" class="no_side_bar">
		<h1>${rc.getMessage("tools.common.404.title")}</h1>
		<p>${rc.getMessage("tools.common.404.description")}</p>
	</div>
</div>
<#include "../inc/footer.ftl">