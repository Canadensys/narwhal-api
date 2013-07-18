<#assign page={"title":rc.getMessage("tools.coordinates.results.heading"),"cssList":["styles/tools.css"]}>
<#include "inc/header.ftl">
<div id="feedback_bar"><a href="https://github.com/Canadensys/narwhal-api/issues/new" target="_blank" title="${rc.getMessage("tools.common.feedback.hover")}">&nbsp;</a></div>
<#include "inc/canadensys-header.ftl">
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
					<#if data.idProvided><td>${(currCoordinate.id?html)!}</td></#if><td class="original">${currCoordinate.originalValue?html}</td><td>${currCoordinate.decimalLatitude?if_exists?if_exists?string("0.#######")}</td><td>${currCoordinate.decimalLongitude?if_exists?if_exists?string("0.#######")}</td>
				</tr>
				<#assign row_odd=!row_odd>
			</#list>
			</tbody>
		</table>
	</div>
</div>
<#include "inc/footer.ftl">