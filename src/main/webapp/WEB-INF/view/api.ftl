<#assign page={"title":rc.getMessage("tools.api.heading"),"cssList":["styles/tools.css"]}>
<#include "inc/header.ftl">
<div id="feedback_bar"><a href="https://github.com/Canadensys/narwhal-api/issues/new" target="_blank" title="${rc.getMessage("tools.common.feedback.hover")}">&nbsp;</a></div>
<#include "inc/canadensys-header.ftl">
<div id="body" class="fullscreen full_height">
	<div id="side_bar">
		<#include "inc/sidebar-list.ftl">
	</div>
	<div id="content" class="clear_fix">
		<h1>${rc.getMessage("tools.api.heading")}</h1>
		<ul class="index">
			<li><a href="#introduction">${rc.getMessage("tools.api.menu.introduction")}</a></li>
			<li><a href="#coordinates">${rc.getMessage("tools.api.menu.coordinates")}</a></li>
			<li><a href="#dates">${rc.getMessage("tools.api.menu.dates")}</a></li>
		</ul>
		
		<a name="introduction"></a>
		<h2>${rc.getMessage("tools.api.menu.introduction")}</h2>
		<p>${rc.getMessage("tools.api.description", ["http://en.wikipedia.org/wiki/JSONP"])}</p>
		
		<a name="coordinates"></a>
		<h2>${rc.getMessage("tools.api.menu.coordinates")}</h2>
		<p class="api-path round">http://data.canadensys.net/tools/coordinates.json<span class="separator">${rc.getMessage("tools.api.uri.separator")}</span>http://data.canadensys.net/tools/coordinates.xml</p>
		<p>${rc.getMessage("tools.api.coordinates.description", ["http://www.geojson.org/", "http://en.wikipedia.org/wiki/Geography_Markup_Language"])}</p>
		<p><em>${rc.getMessage("tools.common.abbrev.example")}</em> http://data.canadensys.net/tools/coordinates.json?<strong>data</strong>=35|45° 32' 25"N,129° 40' 31"W&amp;<strong>callback</strong>=MyCallback</p>
		<p><span>${rc.getMessage("tools.api.output.heading")}</span>
			<pre>
MyCallback({
  type: "FeatureCollection",
  features: [
  {
    type: "Feature",
    geometry: {
      type: "Point",
      coordinates: [-129.6753, 45.5403]
    },
    properties: {
      originalValue: "45° 32' 25"N,129° 40' 31"W"
    },
    id: "35"
  }
  ]
})
			</pre>
		</p>

		<p><em>${rc.getMessage("tools.common.abbrev.example")}</em> http://data.canadensys.net/tools/coordinates.xml?<strong>data</strong>=35|45° 32' 25"N,129° 40' 31"W&amp;<strong>callback</strong>=MyCallback</p>
		<p><span>${rc.getMessage("tools.api.output.heading")}</span>
			<pre>
&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;gml:FeatureCollection
     xmlns:xs="http://www.w3.org/2001/XMLSchema"
     xmlns:xlink="http://www.w3.org/1999/xlink"
     xmlns:gml="http://www.opengis.net/gml"    
     xmlns:sch="http://www.ascc.net/xml/schematron"&gt;
  &lt;gml:featureMembers&gt;
    &lt;xs:result gml:id="35"&gt;
      &lt;xs:coordinate&gt;
        &lt;gml:Point srsDimension="2"&gt;
          &lt;gml:pos&gt;45.540277777777774 -129.67527777777778&lt;/gml:pos&gt;
        &lt;/gml:Point&gt;
      &lt;/xs:coordinate&gt;
      &lt;xs:originalValue&gt;45° 32' 25"N,129° 40' 31"W&lt;/xs:originalValue&gt;
    &lt;/xs:result&gt;
  &lt;/gml:featureMembers&gt;
&lt;/gml:FeatureCollection&gt;
			</pre>
		</p>

		<a name="dates"></a>
		<h2>${rc.getMessage("tools.api.menu.dates")}</h2>
		<p class="api-path round">http://data.canadensys.net/tools/dates.json<span class="separator">${rc.getMessage("tools.api.uri.separator")}</span>http://data.canadensys.net/tools/dates.xml</p>
		<p><em>${rc.getMessage("tools.common.abbrev.example")}</em> http://data.canadensys.net/tools/dates.json?<strong>data</strong>=Jun 13, 2008&amp;<strong>callback</strong>=MyCallback</p>
		<p><span>${rc.getMessage("tools.api.output.heading")}</span>
			<pre>
MyCallback({
  results: [
  {
    originalValue: "Jun 13, 2008",
    year: 2008,
    month: 6,
    day: 13,
    isoDate: "2008-6-13",
    partial: false
  }
  ]
})
			</pre>
		</p>
	</div>
</div>
<#include "inc/footer.ftl">