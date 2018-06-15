<#include "inc/global-functions.ftl">

<head>
<title>${rc.getMessage("tools.coordinates.results.heading")}</title>
<@cssAsset fileName="tools" version=currentVersion! useMinified=false/>
</head>

<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1>${rc.getMessage("tools.coordinates.results.heading")}</h1>
		<table class="narwhal-results">
			<thead>
				<tr>
					<#if (data.idProvided)!false><td class="identifier">id</td></#if><td class="original">${rc.getMessage("tools.common.original")}</td><td>decimalLatitude</td><td>decimalLongitude</td>
				</tr>
			</thead>
			<tbody>
			<#assign row_odd=true>
			<#list data.processedCoordinateList as currCoordinate>
				<tr class=<#if row_odd>"odd<#else>"even</#if><#if (currCoordinate.error)??> error</#if>">
					<#if data.idProvided><td>${(currCoordinate.id?html)!}</td></#if><td class="original">${currCoordinate.originalValue?html}</td><td><#if currCoordinate.decimalLatitude??>${currCoordinate.decimalLatitude?string("0.#######")}</#if></td><td><#if currCoordinate.decimalLongitude??>${currCoordinate.decimalLongitude?string("0.#######")}</#if></td>
				</tr>
				<#assign row_odd=!row_odd>
			</#list>
			</tbody>
		</table>
	</div>
</div>