<#import "spring.ftl" as spring />
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1><@spring.message "about.heading"/></h1>
		<p><@spring.message "about.description"/></p>

		<h2><@spring.message "about.code.heading"/></h2>
		<p><@spring.message "about.code.description"/></p>
	</div>
</div>