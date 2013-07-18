<#assign page={"title":rc.getMessage("tools.dates.results.heading"),"cssList":["styles/tools.css"]}>
<#include "inc/header.ftl">
<div id="feedback_bar"><a href="https://github.com/Canadensys/narwhal-api/issues/new" target="_blank" title="${rc.getMessage("tools.common.feedback.hover")}">&nbsp;</a></div>
<#include "inc/canadensys-header.ftl">
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1>${rc.getMessage("tools.dates.results.heading")}</h1>
		<table class="narwhal-results">
			<thead>
				<tr>
					<#if (data.idProvided)!false><td class="identifier">id</td></#if><td class="original">${rc.getMessage("tools.common.original")}</td><td>${rc.getMessage("tools.common.year")}</td><td>${rc.getMessage("tools.common.month")}</td><td>${rc.getMessage("tools.common.day")}</td><td>${rc.getMessage("tools.common.iso")}</td>
				</tr>
			</thead>
			<tbody>
			<#assign row_odd=true>
			<#list data.processedDateList as currDate>
				<tr class=<#if row_odd>"odd<#else>"even</#if><#if (currDate.error)??> error</#if>">
					<#if data.idProvided><td>${(currDate.id?html)!}</td></#if><td class="original">${currDate.originalValue?html}</td><td>${(currDate.year?c)!}</td><td>${currDate.month!}</td><td>${currDate.day!}</td><td>${currDate.iso8601!}</td>
				</tr>
				<#assign row_odd=!row_odd>
			</#list>
			</tbody>
		</table>
	</div>
</div>
<#include "inc/footer.ftl">