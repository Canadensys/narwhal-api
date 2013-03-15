<#assign page={"title":rc.getMessage("tools.dates.heading"),"cssList":["styles/tools.css"]}>
<#include "inc/header.ftl">
<div id="feedback_bar"><a href="https://github.com/Canadensys/narwhal-api/issues/new" target="_blank" title="${ltext("tools.common.feedback.hover")}">&nbsp;</a></div>
<#include "inc/canadensys-header.ftl">
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
		<div class="side_menu">
			<h2>${rc.getMessage("tools.common.examples.heading")}</h2>
			<ul>
				<li>Jun 13, 2008</li>
				<li>15 Jan 2011</li>
				<li>2009 IV 02</li>
				<li>2 VII 1986</li>
				<li>&nbsp;</li>
				<li>1 | 1999/02/24</li>
				<li>2 | 02/17/1921</li>
			</ul>
		</div>
	</div>
	<div id="content" class="clear_fix">
		<h1>${rc.getMessage("tools.dates.heading")}</h1>
		<form class="narwhal-form" method="post">
			<p>${rc.getMessage("tools.dates.description")}</p>
			<div class="round custom-form">
				<textarea name="data" rows="15" class="round" placeholder="Jun 13, 2008"></textarea>
				<input type="submit" value="${rc.getMessage("tools.common.form.submit")}" class="round" />
			</div>
		</form>
	</div>
</div>
<#include "inc/footer.ftl">
