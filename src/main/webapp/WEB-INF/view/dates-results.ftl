<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/api-sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1>Date parsing results</h1>
		
		<p>WITHOUT ID</p>
		
		<table class="narwhal-results">
			<thead>
				<tr>
					<td class="original">original</td><td>year</td><td>month</td><td>day</td><td>isoDate</td>
				</tr>
			</thead>
			<tbody>
				<tr class="odd">
					<td class="original">May 12, 2001</td><td>2001</td><td>05</td><td>12</td><td>2001-05-12</td>
				</tr>
				<tr class="even">
					<td class="original">1999 XII 24</td><td>1999</td><td>12</td><td>24</td><td>1999-12-24</td>
				</tr>
			</tbody>
		</table>
		
		<p>WITH ID</p>
		
		<table class="narwhal-results">
			<thead>
				<tr>
					<td class="identifier">id</td><td class="original">original</td><td>year</td><td>month</td><td>day</td><td>isoDate</td>
				</tr>
			</thead>
			<tbody>
				<tr class="odd">
					<td>1</td><td class="original">May 12, 2001</td><td>2001</td><td>05</td><td>12</td><td>2001-05-12</td>
				</tr>
				<tr class="even">
					<td>2</td><td class="original">1999 XII 24</td><td>1999</td><td>12</td><td>24</td><td>1999-12-24</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
