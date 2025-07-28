# backend
all for java.version = 1.8
- [Kotlin](https://kotlinlang.org/) 1.2.71  
- [SpringBoot](https://spring.io/projects/spring-boot) 2.1.3
- [maven-resources-plugin](https://maven.apache.org/plugins/maven-resources-plugin/) for copy vue files
## Tests
- [AuthControllerTest](./src/kotlin/test/com/tor/kotlin/spring/backend/controllers/AuthControllerTest.kt) article [youtube]( https://youtu.be/CbI_EQ59Sy8?t=2043)

### JpaTests
- [JpaSpecApplicationTests](com.tor.kotlin.spring.backend.repo.JpaSpecApplicationTests) article [Testing Those Specifications](https://dzone.com/articles/testing-those-specifications)
### Rest Test
io.restassured.RestAssured
- [rest-assured.io](https://rest-assured.io/)
- [gitHub](https://github.com/rest-assured/rest-assured)
- [Complete Guide to API Testing Using Rest Assured: Tips, Techniques, and Best Practices](https://www.jadeglobal.com/blog/complete-guide-api-testing-using-rest-assured-tips-techniques-and-best-practices#:~:text=Rest%20Assured%20is%20an%20open,framework%20for%20writing%20test%20cases.) 
- [Ensuring Rest API compatibility with Spring Cloud Contract](https://rpestano.wordpress.com/2021/12/28/ensuring-rest-api-compatibility-with-spring-cloud-contract/)
- [git example](https://github.com/rmpestano/rest-api-contracts/blob/v1/addition-api/src/test/kotlin/com/github/rmpestano/addition/ContractBase.kt)
- [Spring Cloud Contract](https://spring.io/projects/spring-cloud-contract)

```xml 
        <dependency>
			<groupId>io.rest-assured</groupId>
			<artifactId>spring-web-test-client</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-contract-spec-kotlin</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-contract-verifier</artifactId>
			<scope>test</scope>
		</dependency>
```

## Links
- [SpringBootRefreshTokenJWT](https://github.com/Rapter1990/SpringBootRefreshTokenJWT)
- [Тестирование Spring Boot через MockMVC](https://habr.com/ru/companies/otus/articles/746414/)
- [Тестирование Spring Security](https://habr.com/ru/articles/795149/)   
- [Better Testing with Spring Security Test](https://developer.okta.com/blog/2021/05/19/spring-security-testing)
- [Функциональное тестирование в SpringBoot](https://habr.com/ru/companies/otus/articles/747852/)
- [Testing examples](https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-testing)
- [Testing an OAuth Secured API with Spring MVC (using the Spring Security OAuth legacy stack)](https://www.baeldung.com/oauth-api-testing-with-spring-mvc)
- [Kotlin - How to manage @BeforeClass static method in springBootTest](https://stackoverflow.com/questions/51612019/kotlin-how-to-manage-beforeclass-static-method-in-springboottest)
- [How to UNIT test Spring @PreAuthorize(hasAuthority)?](https://stackoverflow.com/questions/62552926/how-to-unit-test-spring-preauthorizehasauthority)    
- [Spring Boot – Profile based properties and yaml example](https://www.mkyong.com/spring-boot/spring-boot-profile-based-properties-and-yaml-example/)
- [GH Spring Boot JWT Authentication example with Spring Security & Spring Data JPA](https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication)
- [GH Master Spring and Spring Boot](https://github.com/in28minutes/master-spring-and-spring-boot)
- [GH Spring Boot 3.0 Security with JWT Implementation](https://github.com/ali-bouali/spring-boot-3-jwt-security)
- [Custom Password Encoder in Spring Security](https://www.appsdeveloperblog.com/custom-password-encoder-in-spring-security/)
#### Swagger && OpenAPI
- [Генерация OpenAPI из Spring Boot MVC](https://habr.com/ru/articles/814061/)
#### SBOM
- [SBOM в Spring Boot: от генерации до анализа уязвимостей](https://habr.com/ru/companies/spring_aio/articles/899018/)
- [Как создавать SBOM в Java с помощью Maven и Gradle](https://habr.com/ru/articles/723214/)
## to do
- java need upgrade
- kotlin need upgrade
- springBoot need upgrade 3.4
- SBOM
- see  ..bezkoder.. Role pull request src/main/java/com/bezkoder/springjwt/SpringBootSecurityJwtApplication.java