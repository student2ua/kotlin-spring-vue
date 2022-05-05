# frontend

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
[Configuration Reference](https://cli.vuejs.org/config/).

### all in one :)
```
vue create frontend
vue ui
npm install --save vue-router
npm install --save axios
npm install --save vuex
npm install --save bootstrap bootstrap-vue
```   
## Links
- [Организация работы с API на Vue.js Slide](https://www.slideshare.net/MailRuGroup/api-vuejs-175897016)
- [Организация работы с API на Vue.js Youtube](https://www.youtube.com/watch?v=42GnGAa3654)
- [http-vue-loader](https://www.npmjs.com/package/http-vue-loader)
- [PurgeCSS-vue.js](https://purgecss.com/guides/vue.html#use-the-vue-cli-plugin)
- [управлять заголовками и мета-тегами в vuejs head, meta, title](https://qna.habr.com/q/490912)
- [route-проверка входа](https://webdevblog.ru/ispolzovanie-middleware-vo-vue/)
- [Toast bootstrap-vue](https://bootstrap-vue.org/docs/components/toast)
- [handsontable 7.4.2](https://handsontable.com/docs/7.4.2/frameworks-wrapper-for-vue-vuex-example.html)
- [A login page with vuex and vuetify](https://www.retinadata.com/blog/a-login-page-with-vuex-and-vuetify/)
### axios
- [Используем axios вместе с Vue.js](https://medium.com/devschacht/используем-axios-вместе-с-vue-js-3bc45464c460)
- [Axios interceptor to refresh JWT token after expiration](https://blog.liplex.de/axios-interceptor-to-refresh-jwt-token-after-expiration/)
- [see interceptor](https://github.com/yangzongzhuan/RuoYi-Vue/blob/master/ruoyi-ui/src/utils/request.js)
- [How can you use axios interceptors?](https://stackoverflow.com/questions/52737078/how-can-you-use-axios-interceptors)
## to do
- [s1](https://github.com/rieckpil/testing-spring-boot-applications-masterclass/blob/master/src/frontend/public/index.html)
- [Make Blacklist JWT tokens for spring boot](https://stackoverflow.com/questions/57674199/make-blacklist-jwt-tokens-for-spring-boot)
- [Spring-boot JWT logout](https://stackoverflow.com/questions/34475946/spring-boot-jwt-logout)
- [error0308010](https://stackoverflow.com/questions/69692842/error-message-error0308010cdigital-envelope-routinesunsupported)
1. Try to uninstall Node.js version 17+ and reinstall the Node.js version 16+

2. Open terminal and paste these as described :

Linux & Mac OS (windows git bash)- `export NODE_OPTIONS=--openssl-legacy-provider`

Windows command prompt- `set NODE_OPTIONS=--openssl-legacy-provider`
### pusher
- [Calendar event scheduling in Vue](https://pusher.com/tutorials/calendar-vue/)
`npm install pusher-js`

### SSE

- [Server-Sent Events: пример использования](https://habr.com/ru/post/519982/)
- [vue-sse component](https://github.com/tserkov/vue-sse)

## text info
- [awesome-vue](https://github.com/vuejs/awesome-vue#table)
- [Проектирование архитектуры хранилища Vuex для больших приложений на Vue.js](https://habr.com/ru/company/otus/blog/506754/)
- [Улучшение производительности vue приложения](https://habr.com/ru/post/543298/)
- [WebPack Code Splitting](https://webpack.js.org/guides/code-splitting/)
#### headers
- [securityheaders - mark](https://https://www.npmjs.com/package/http-vue-loader)
- [HTTP-заголовки для ответственного разработчика](https://habr.com/ru/company/mailru/blog/450816/)
- [Сервис проверки HTTP-заголовков сервера](https://habr.com/ru/post/504220/) [проверить](securityheaders.com/?q=https%3A%2F%2Fmark.hneu.edu.ua%2FopenAPI%2F%23&followRedirects=on)
- [Как использовать HTTP заголовки для предупреждения уязвимостей](https://habr.com/ru/company/hosting-cafe/blog/315802/)
- [HPKP: HTTP Public Key Pinning *Устарел*](https://scotthelme.co.uk/hpkp-http-public-key-pinning/)
- [Как усилить защищенность веб-приложений при помощи HTTP заголовков](https://habr.com/ru/company/edison/blog/434228/)

`./letsencrypt-auto --hsts`

##### Strict-Transport-Security
Этот заголовок говорит браузеру, что вы не хотите использовать HTTP-запросы, и тогда он автоматически применит те же запросы к такому же источнику с защищенным соединением. Если вы попытаетесь открыть такой же URL через HTTP, браузер снова будет использовать HTTPS и перенаправит пользователя.

Вы можете настроить, как долго этот параметр должен оставаться активным (max-age в секундах), если захотите потом снова использовать HTTP. Если вы хотите включить поддомены, то можете настроить это с помощью *includeSubDomains*.

Если вы хотите сделать всё возможное, чтобы браузер никогда не запрашивал ваш сайт по HTTP, можете также задать указатель preload и отправить ваш сайт в [глобальный список](https://hstspreload.org/). Если конфигурация HSTS вашего сайта соответствует минимальному *max-age* в один год и активна для поддоменов, он может быть включен во внутренний список браузер для сайтов, работающих только через HTTPS.

`Strict-Transport-Security: max-age=1000; includeSubDomains; preload`
##### Content-Security-Policy
`Content-Security-Policy: upgrade-insecure-requests`
Указатель upgrade-insecure-requests заставляет браузер переделать все HTTP-запросы в HTTPS-запросы.

`Content-Security-Policy-Report-Only: default-src 'self'; ... report-uri https://stefanjudis.report-uri.com/r/d/csp/reportOnly`
Используя режим Content-Security-Policy-Report-Only, браузеры просто записывают ресурсы, которые были бы заблокированы, вместо их фактической блокировки. Этот механизм отчетности позволяет проверить и настроить ваш набор правил.
##### Cache-Control
`Cache-Control: max-age=30, public`  как долго ресурс должен считаться «свежим» и может отдавать из кэша браузера.
 **Immutable** — никогда не запрашивать ресурс дважды
 ##### Accept-Encoding
` Accept-Encoding: gzip, deflate, br `

