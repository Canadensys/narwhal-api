<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1><@@spring.message "dates.results.heading"/></h1>
		<table class="narwhal-results">
			<thead>
				<tr>
					<#if root.idProvided><td class="identifier">id</td></#if><td class="original">original</td><td>year</td><td>month</td><td>day</td><td>isoDate</td>
				</tr>
			</thead>
			<tbody>
			<#assign row_odd=true>
			<#list root.results as currDate>
				<tr class=<#if row_odd>"odd"<#else>"even"</#if>>
					<#if root.idProvided><td>${currDate.id}</td></#if><td class="original">${currDate.originalValue}</td><td>${currDate.year?if_exists}</td><td>${currDate.month?if_exists}</td><td>${currDate.day?if_exists}</td><td>${currDate.isoDate?if_exists}</td>
				</tr>
				<#assign row_odd=!row_odd>
			</#list>
			</tbody>
		</table>
	</div>
</div>
