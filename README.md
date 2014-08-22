newrelic-rum-liferay
====================

Liferay hook that inject custom variables in Velocity to do New Relic manual instrumentation of web pages.

Take a look at the [discussion](https://discuss.newrelic.com/t/howto-real-user-monitoring-in-liferay-portal-6-1-6-2/4293) on New Relic forums [1]

At Noovle we have need to use the awesome RUM metrics to inspect our Liferay Portal installation.

First the auto instrumentation isn't work for us, we think because Liferay is a complex platform and doesn't do the fully standard flow to generate pages, but no headache we got solution!

We follow the instructions from NewRelic [documentation](https://docs.newrelic.com/docs/agents/java-agent/instrumentation/page-load-timing-java#manual_instrumentation) to do manual instrumentation.
To do this, in Liferay, is necessary create a custom bean locator as explain [here](https://www.liferay.com/web/pmesotten/blog/-/blogs/inject-any-custom-class-or-service-into-web-content-templates)

In this repo is present a velocity-hook that inject a new bean in the Velocity context.

_After_ deploy the hook the following snippet are _required_ in the Liferay theme.

This after META tags
```
#set ($NewRelicTool = $utilLocator.findUtil("velocity-hook", "it.noovle.open.liferay.newrelic.NewRelicWrapper"))
<!-- NewRelic RUM START-->
$NewRelicTool.getBrowserTimingHeader()
<!-- NewRelic RUM END -->
```

This before the </body>
```
<!-- NewRelic RUM START-->
$NewRelicTool.getBrowserTimingFooter()
<!-- NewRelic RUM END -->
```
