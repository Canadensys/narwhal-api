<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/api-sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1>Coordinate conversion results</h1>		
		<table class="narwhal-results">
			<thead>
				<tr>
					<#if root.idProvided><td class="identifier">id</td></#if><td class="original">original</td><td>decimalLatitude</td><td>decimalLongitude</td>
				</tr>
			</thead>
			<tbody>
			<#assign row_odd=true>
			<#list root.results as currCoordinate>
				<tr class=<#if row_odd>"odd"<#else>"even"</#if>>
					<#if root.idProvided><td>${currCoordinate.id}</td></#if><td class="original">${currCoordinate.originalValue}</td><td>${currCoordinate.decimalLatitude?if_exists}</td><td>${currCoordinate.decimalLongitude?if_exists}</td>
				</tr>
				<#assign row_odd=!row_odd>
			</#list>
			</tbody>
		</table>
	</div>
</div>