/**
 * QPlatform anugular js application initialization
 *
 */
(function () {
    angular.module('apsf', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        //'ui.bootstrap',                 // Ui Bootstrap
        'pascalprecht.translate',       // Angular Translate
        'ngIdle',                        // Idle timer
        'ngResource',
        'spinnerApp',
        //'ngAnimate',
        'toaster',
        'ngCookies',
        //'datatables',
        //'datatables.colreorder'
    ])
})();

// Other libraries are loaded dynamically in the config.js file using the library ocLazyLoad