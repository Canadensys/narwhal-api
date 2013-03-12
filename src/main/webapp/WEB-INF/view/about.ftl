<#import "spring.ftl" as spring />
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1><@spring.message "about.heading"/></h1>
		<p>${rc.getMessage("about.description", ["http://data.canadensys.net"])}</p>

		<h2><@spring.message "about.code.heading"/></h2>
		<p>${rc.getMessage("about.code.description", ["https://github.com/Canadensys/narwhal-processor"])}</p></p>
	</div>
</div>