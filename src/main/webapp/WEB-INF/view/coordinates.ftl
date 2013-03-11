<#import "spring.ftl" as spring />
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<ul class="side_menu custom_list round">
			<li><a href="coordinates.html">Coordinate conversion</a></li>
			<li><a href="dates.html">Date parsing</a></li>
			<li><a href="api.html">Tools API</a></li>
			<li><a href="about.html">About</a></li>
		</ul>
		<div class="side_menu">
			<h2>Example input</h2>
			<ul>
				<li>45° 32' 25&quot; N, 129° 40' 31&quot; W</li>
				<li>&nbsp;</li>
				<li>1 | 45.5° N, 129.6° W</li>
				<li>2 | 40°26′47″N,74° 0' 21.5022"W</li>
			</ul>
		</div>
	</div>
	<div id="content" class="clear_fix">
		<h1><@spring.message "api.coordinates.heading"/></h1>
		<form class="narwhal-form" method="post" action="coordinates.html">
			<p><@spring.message "api.coordinates.description"/></p>
			<div class="round custom-form">
				<textarea name="data" rows="15" class="round" placeholder="45° 32' 25&quot; N, -129° 40' 31&quot; W"></textarea>
				<input type="submit" value="Submit" class="round" />
			</div>
		</form>
	</div>
</div>