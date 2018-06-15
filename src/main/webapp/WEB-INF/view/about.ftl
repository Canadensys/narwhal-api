<#include "inc/global-functions.ftl">

<head>
<title>${rc.getMessage("tools.about.heading")}</title>
<@cssAsset fileName="tools" version=currentVersion! useMinified=false/>
</head>

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
