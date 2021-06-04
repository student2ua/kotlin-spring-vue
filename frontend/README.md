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
- [Используем axios вместе с Vue.js](https://medium.com/devschacht/используем-axios-вместе-с-vue-js-3bc45464c460)
- [Организация работы с API на Vue.js Slide](https://www.slideshare.net/MailRuGroup/api-vuejs-175897016)
- [Организация работы с API на Vue.js Youtube](https://www.youtube.com/watch?v=42GnGAa3654)
- [http-vue-loader](https://www.npmjs.com/package/http-vue-loader)
- [PurgeCSS-vue.js](https://purgecss.com/guides/vue.html#use-the-vue-cli-plugin)
### text info

#### headers
- [HTTP-заголовки для ответственного разработчика](https://habr.com/ru/company/mailru/blog/450816/)
- [Сервис проверки HTTP-заголовков сервера](https://habr.com/ru/post/504220/)

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

