<#import "spring.ftl" as spring />
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1><@spring.message "api.heading"/></h1>
		<ul class="index">
			<li><a href="#introduction"><@spring.message "api.menu.introduction"/></a></li>
			<li><a href="#coordinates"><@spring.message "api.menu.coordinates"/></a></li>
			<li><a href="#dates"><@spring.message "api.menu.dates"/></a></li>
		</ul>
		
		<a name="introduction"></a>
		<h2><@spring.message "api.menu.introduction"/></h2>
		<p><@spring.message "api.description"/></p>
		
		<a name="coordinates"></a>
		<h2><@spring.message "api.menu.coordinates"/></h2>
		<p class="api-path round">http://data.canadensys.net/tools/coordinates.json<span class="separator"><@spring.message "api.uri.separator"/></span>http://data.canadensys.net/tools/coordinates.xml</p>
		<p><@spring.message "api.coordinates.description"/></p>
		<p><em><@spring.message "pages.abbrev.example"/></em> http://data.canadensys.net/tools/coordinates.json?<strong>data</strong>=35|45° 32' 25"N,-129° 40' 31W"&amp;<strong>callback</strong>=MyCallback</p>
		<p><span><@spring.message "api.output.heading"/></span><pre></pre></p>
		
		<a name="dates"></a>
		<h2><@spring.message "api.menu.dates"/></h2>
		<p class="api-path round">http://data.canadensys.net/tools/dates.json<span class="separator"><@spring.message "api.uri.separator"/></span>http://data.canadensys.net/tools/dates.xml</p>
		<p><em><@spring.message "pages.abbrev.example"/></em> http://data.canadensys.net/tools/dates.json?<strong>data</strong>=Jun 13, 2008&amp;<strong>callback</strong>=MyCallback</p>
		<p><span><@spring.message "api.output.heading"/></span><pre></pre></p>
	</div>
</div>