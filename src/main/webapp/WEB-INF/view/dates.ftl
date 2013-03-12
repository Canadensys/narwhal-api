<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/api-sidebar-list.ftl">
		<div class="side_menu">
			<h2>Example input</h2>
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
		<h1>Date parsing</h1>
		<form class="narwhal-form" method="post" action="dates_results.html">
			<p>Use this tool to parse dates into their component parts. Type or paste dates on separate lines, optionally preceded by your own identifier followed by a tab or a pipe</p>
			<div class="round custom-form">
				<textarea name="data" rows="15" class="round" placeholder="Jun 13, 2008"></textarea>
				<input type="submit" value="Submit" class="round" />
			</div>
		</form>
	</div>
</div>