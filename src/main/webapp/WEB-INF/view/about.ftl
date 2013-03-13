<#assign page={"title":rc.getMessage("tools.about.heading"),"cssList":["styles/tools.css"]}>
<#include "inc/header.ftl">
<#include "inc/canadensys-header.ftl">
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1>${rc.getMessage("tools.about.heading")}</h1>
		<p>${rc.getMessage("tools.about.description", ["http://data.canadensys.net"])}</p>

		<h2>${rc.getMessage("tools.about.code.heading")}</h2>
		<p>${rc.getMessage("tools.about.code.description", ["https://github.com/Canadensys/narwhal-processor"])}</p></p>
	</div>
</div>
<#include "inc/footer.ftl">