/*
 * AngularJS Toaster
 * Version: 0.4.15
 *
 * Copyright 2013-2015 Jiri Kavulak.
 * All Rights Reserved.
 * Use, reproduction, distribution, and modification of this code is subject to the terms and
 * conditions of the MIT license, available at http://www.opensource.org/licenses/mit-license.php
 *
 * Author: Jiri Kavulak
 * Related to project of John Papa, Hans Fjällemark and Nguyễn Thiện Hùng (thienhung1989)
 */
/*
 * AngularJS Toaster
 * Version: 0.4.15
 *
 * Copyright 2013-2015 Jiri Kavulak.
 * All Rights Reserved.
 * Use, reproduction, distribution, and modification of this code is subject to the terms and
 * conditions of the MIT license, available at http://www.opensource.org/licenses/mit-license.php
 *
 * Author: Jiri Kavulak
 * Related to project of John Papa, Hans Fjällemark and Nguyễn Thiện Hùng (thienhung1989)
 */
! function() {
    "use strict";
    angular.module("toaster", []).constant("toasterConfig", {
        limit: 0,
        "tap-to-dismiss": !0,
        "close-button": !1,
        "newest-on-top": !0,
        "time-out": 5e3,
        "icon-classes": {
            error: "toast-error",
            info: "toast-info",
            wait: "toast-wait",
            success: "toast-success",
            warning: "toast-warning"
        },
        "body-output-type": "",
        "body-template": "toasterBodyTmpl.html",
        "icon-class": "toast-info",
        "position-class": "toast-top-right",
        "title-class": "toast-title",
        "message-class": "toast-message",
        "prevent-duplicates": !1,
        "mouseover-timer-stop": !0
    }).service("toaster", ["$rootScope", "toasterConfig", function(t, e) {
        function o(t) {
            return function(e, o, s, i, n, a, r, c, l) {
                angular.isString(e) ? this.pop(t, e, o, s, i, n, a, r, c, l) : this.pop(angular.extend(e, {
                    type: t
                }))
            }
        }
        this.pop = function(e, o, s, i, n, a, r, c, l, u) {
            if (angular.isObject(e)) {
                var d = e;
                this.toast = {
                    type: d.type,
                    title: d.title,
                    body: d.body,
                    timeout: d.timeout,
                    bodyOutputType: d.bodyOutputType,
                    clickHandler: d.clickHandler,
                    showCloseButton: d.showCloseButton,
                    uid: d.toastId,
                    onHideCallback: d.onHideCallback
                }, l = d.toastId, r = d.toasterId
            } else this.toast = {
                type: e,
                title: o,
                body: s,
                timeout: i,
                bodyOutputType: n,
                clickHandler: a,
                showCloseButton: c,
                uid: l,
                onHideCallback: u
            };
            t.$emit("toaster-newToast", r, l)
        }, this.clear = function(e, o) {
            t.$emit("toaster-clearToasts", e, o)
        };
        for (var s in e["icon-classes"]) this[s] = o(s)
    }]).factory("toasterEventRegistry", ["$rootScope", function(t) {
        var e, o = null,
            s = null,
            i = [],
            n = [];
        return e = {
            setup: function() {
                o || (o = t.$on("toaster-newToast", function(t, e, o) {
                    for (var s = 0, n = i.length; n > s; s++) i[s](t, e, o)
                })), s || (s = t.$on("toaster-clearToasts", function(t, e, o) {
                    for (var s = 0, i = n.length; i > s; s++) n[s](t, e, o)
                }))
            },
            subscribeToNewToastEvent: function(t) {
                i.push(t)
            },
            subscribeToClearToastsEvent: function(t) {
                n.push(t)
            },
            unsubscribeToNewToastEvent: function(t) {
                var e = i.indexOf(t);
                e >= 0 && i.splice(e, 1), 0 === i.length && (o(), o = null)
            },
            unsubscribeToClearToastsEvent: function(t) {
                var e = n.indexOf(t);
                e >= 0 && n.splice(e, 1), 0 === n.length && (s(), s = null)
            }
        }, {
            setup: e.setup,
            subscribeToNewToastEvent: e.subscribeToNewToastEvent,
            subscribeToClearToastsEvent: e.subscribeToClearToastsEvent,
            unsubscribeToNewToastEvent: e.unsubscribeToNewToastEvent,
            unsubscribeToClearToastsEvent: e.unsubscribeToClearToastsEvent
        }
    }]).directive("directiveTemplate", ["$compile", function(t) {
        return {
            restrict: "A",
            scope: {
                directiveName: "@directiveName"
            },
            replace: !0,
            link: function(e, o) {
                e.$watch("directiveName", function(s) {
                    var i = t("<div " + s + "></div>")(e);
                    o.append(i)
                })
            }
        }
    }]).directive("toasterContainer", ["$parse", "$rootScope", "$interval", "$sce", "toasterConfig", "toaster", "toasterEventRegistry", function(t, e, o, s, i, n, a) {
        return {
            replace: !0,
            restrict: "EA",
            scope: !0,
            link: function(e, r, c) {
                function l(t, s) {
                    t.timeoutPromise = o(function() {
                        e.removeToast(t.id)
                    }, s, 1)
                }

                function u(o, i) {
                    if (o.type = v["icon-classes"][o.type], o.type || (o.type = v["icon-class"]), v["prevent-duplicates"] === !0)
                        if (m(i)) {
                            if (e.toasters.length > 0 && e.toasters[e.toasters.length - 1].body === o.body) return
                        } else {
                            var n, a;
                            for (n = 0, a = e.toasters.length; a > n; n++) e.toasters[n].uid === i && (d(n), n--, a = e.toasters.length)
                        }
                    o.id = ++f, m(i) || (o.uid = i);
                    var r = v["close-button"];
                    if ("boolean" == typeof o.showCloseButton);
                    else if ("boolean" == typeof r) o.showCloseButton = r;
                    else if ("object" == typeof r) {
                        var c = r[o.type];
                        "undefined" != typeof c && null !== c && (o.showCloseButton = c)
                    } else o.showCloseButton = !1;
                    switch (o.bodyOutputType = o.bodyOutputType || v["body-output-type"], o.bodyOutputType) {
                        case "trustedHtml":
                            o.html = s.trustAsHtml(o.body);
                            break;
                        case "template":
                            o.bodyTemplate = o.body || v["body-template"];
                            break;
                        case "templateWithData":
                            var l = t(o.body || v["body-template"]),
                                u = l(e);
                            o.bodyTemplate = u.template, o.data = u.data;
                            break;
                        case "directive":
                            o.html = o.body
                    }
                    e.configureTimer(o), v["newest-on-top"] === !0 ? (e.toasters.unshift(o), v.limit > 0 && e.toasters.length > v.limit && e.toasters.pop()) : (e.toasters.push(o), v.limit > 0 && e.toasters.length > v.limit && e.toasters.shift())
                }

                function d(t) {
                    var s = e.toasters[t];
                    s && (s.timeoutPromise && o.cancel(s.timeoutPromise), e.toasters.splice(t, 1), angular.isFunction(s.onHideCallback) && s.onHideCallback())
                }

                function p(t) {
                    for (var o = e.toasters.length - 1; o >= 0; o--) m(t) ? d(o) : e.toasters[o].uid == t && d(o)
                }

                function m(t) {
                    return angular.isUndefined(t) || null === t
                }
                var v, f = 0;
                v = angular.extend({}, i, e.$eval(c.toasterOptions)), e.config = {
                    toasterId: v["toaster-id"],
                    position: v["position-class"],
                    title: v["title-class"],
                    message: v["message-class"],
                    tap: v["tap-to-dismiss"],
                    closeButton: v["close-button"],
                    animation: v["animation-class"],
                    mouseoverTimer: v["mouseover-timer-stop"]
                }, e.$on("$destroy", function() {
                    a.unsubscribeToNewToastEvent(e._onNewToast), a.unsubscribeToClearToastsEvent(e._onClearToasts)
                }), e.configureTimer = function(t) {
                    var e = angular.isNumber(t.timeout) ? t.timeout : v["time-out"];
                    "object" == typeof e && (e = e[t.type]), e > 0 && l(t, e)
                }, e.removeToast = function(t) {
                    var o, s;
                    for (o = 0, s = e.toasters.length; s > o; o++)
                        if (e.toasters[o].id === t) {
                            d(o);
                            break
                        }
                }, e.toasters = [], e._onNewToast = function(t, o, s) {
                    (m(e.config.toasterId) && m(o) || !m(e.config.toasterId) && !m(o) && e.config.toasterId == o) && u(n.toast, s)
                }, e._onClearToasts = function(t, o, s) {
                    ("*" == o || m(e.config.toasterId) && m(o) || !m(e.config.toasterId) && !m(o) && e.config.toasterId == o) && p(s)
                }, a.setup(), a.subscribeToNewToastEvent(e._onNewToast), a.subscribeToClearToastsEvent(e._onClearToasts)
            },
            controller: ["$scope", "$element", "$attrs", function(t) {
                t.stopTimer = function(e) {
                    t.config.mouseoverTimer === !0 && e.timeoutPromise && (o.cancel(e.timeoutPromise), e.timeoutPromise = null)
                }, t.restartTimer = function(e) {
                    t.config.mouseoverTimer === !0 ? e.timeoutPromise || t.configureTimer(e) : null === e.timeoutPromise && t.removeToast(e.id)
                }, t.click = function(e, o) {
                    if (t.config.tap === !0 || e.showCloseButton === !0 && o === !0) {
                        var s = !0;
                        e.clickHandler && (angular.isFunction(e.clickHandler) ? s = e.clickHandler(e, o) : angular.isFunction(t.$parent.$eval(e.clickHandler)) ? s = t.$parent.$eval(e.clickHandler)(e, o) : console.log("TOAST-NOTE: Your click handler is not inside a parent scope of toaster-container.")), s && t.removeToast(e.id)
                    }
                }
            }],
            template: '<div id="toast-container" ng-class="[config.position, config.animation]"><div ng-repeat="toaster in toasters" class="toast" ng-class="toaster.type" ng-click="click(toaster)" ng-mouseover="stopTimer(toaster)" ng-mouseout="restartTimer(toaster)"><button type="button" class="toast-close-button" ng-show="toaster.showCloseButton" ng-click="click(toaster, true)">&times;</button><div ng-class="config.title">{{toaster.title}}</div><div ng-class="config.message" ng-switch on="toaster.bodyOutputType"><div ng-switch-when="trustedHtml" ng-bind-html="toaster.html"></div><div ng-switch-when="template"><div ng-include="toaster.bodyTemplate"></div></div><div ng-switch-when="templateWithData"><div ng-include="toaster.bodyTemplate"></div></div><div ng-switch-when="directive"><div directive-template directive-name="{{toaster.html}}"></div></div><div ng-switch-default >{{toaster.body}}</div></div></div></div>'
        }
    }])
}(window, document);