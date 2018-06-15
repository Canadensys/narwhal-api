<#include "inc/global-functions.ftl">

<head>
<title>${rc.getMessage("tools.coordinates.heading")}</title>
<@cssAsset fileName="tools" version=currentVersion! useMinified=false/>
</head>

<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
		<div class="side_menu">
			<h2>${rc.getMessage("tools.common.examples.heading")}</h2>
			<ul>
				<li>45° 32' 25&quot; N, 129° 40' 31&quot; W</li>
				<li>&nbsp;</li>
				<li>1 | 45.5° N, 129.6° W</li>
				<li>2 | 40°26′47″N,74° 0' 21.5022"W</li>
			</ul>
		</div>
	</div>
	<div id="content" class="clear_fix">
		<h1>${rc.getMessage("tools.coordinates.heading")}</h1>
		<form class="narwhal-form" method="post">
			<p>${rc.getMessage("tools.coordinates.description")}</p>
			<div class="round custom-form">
				<textarea name="data" rows="15" class="round" placeholder="45° 32' 25&quot; N, 129° 40' 31&quot; W"></textarea>
				<input type="submit" value="${rc.getMessage("tools.common.form.submit")}" class="round" />
			</div>
		</form>
	</div>
</div>
