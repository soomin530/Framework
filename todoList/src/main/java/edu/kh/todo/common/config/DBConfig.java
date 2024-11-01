package edu.kh.todo.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 * @Configuration
 * - 스프링 설정용 클래스임을 명시 (스프링이 해당 클래스를 설정 정보로 인식하고 사용)
 * + 객체(Bean)로 생성해서 내부 코드를 서버 실행시 모두 바로 실행
 * 
 * @PropertySource
 * - 지정된 경로의 properties 파일 내용을 읽어와 사용
 * - 사용할 properties 파일이 다수일 경우
 * 	 해당 어노테이션 연속해서 작성 가능
 * 
 * - classpath:/ 는 src/main/resources 경로를 의미!
 * 
 * @Autowired
 * - 등록된 Bean 중에서
 * 	 타입이 일치하거나, 상속 관계에 있는 Bean을
 * 	 지정된 필드에 주입
 * 	 == 의존성 주입(DI)
 * 
 * @ConfigurationProperties(prefix = "spring.datasource.hikari")
 * - @PropertySource 로 읽어온 config.properties 파일의 내용 중
 * 	 접두사(앞부분, prefix)가 일치하는 값만 읽어옴
 * 
 * @Bean
 * - 개발자가 수동으로 생성한 객체의 관리를
 * 	 스프링에게 넘기는 어노테이션(Bean) 등록
 * 	 -> 개발자가 직접 객체 만들었지만 (new)
 * 		Bean으로 등록해서 Spring이 관리하도록 해줌!
 * 
 * 	 - @Bean 어노테이션이 작성된 메서드에서 반환된 객체는
 * 	   Spring Container가 관리함(IOC)
 * 	   @ConfigurationProperties(prefix = "spring.datasource.hikari")
 * 
 *     properties 파일의 내용을 이용해서 생성되는 bean을 설정하는 어노테이션
 *     prefix를 지정하여 spring.datasource.hikari으로 시작하는 설정을 모두 적용
 * */

@Configuration
@PropertySource("classpath:/config.properties")
public class DBConfig {

	// 필드

	// import org.springframework.context.ApplicationContext
	@Autowired // <- 의존성 주입(DI) 어노테이션
	private ApplicationContext applicationContext; // application scope 객체 : 즉, 현재 프로젝트
	// 스프링이 관리하고 있는 ApplicationContext 객체를 의존성 주입 받는다
	// -> 현재 프로젝트의 전반적인 설정과 Bean 관리에 접근할 수 있도록 해줌
	// -> 때문에 applicationContext 비어있지 않고 객체 들어있는 상태!

	// 메서드

	////////////////////// HikariCP 설정 //////////////////////

	@Bean 					// 접두사
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {

		// -> config.properties 파일에서 읽어온
		// spring.datasource.hikari 로 시작하는 모든 값이
		// 자동으로 알맞은 필드에 세팅됨

		return new HikariConfig();
	}

	@Bean // 데이터 소스도 Bean으로 등록
	public DataSource dataSource(HikariConfig config) { // 데이터 소스 만들 때 HikariConfig 받아 사용!
		// 매개변수 HikariConfig config
		// -> 등록된 Bean 중 HikariConfig 타입의 Bean을 자동으로 주입
		// -> HikariConfig 객체를 받아,
		// 설정된 HikariConfig를 통해 DataSource 객체를 생성

		DataSource dataSource = new HikariDataSource(config);
		// DataSource : 애플리케이션이 데이터베이스에 연결할 때 사용하는 설정
		// 1) DB 연결 정보 제공 (url, username, password)
		// 2) Connection pool 관리
		// 3) 트랜잭션 관리 (commit, rollback)

		return dataSource;
	}

	//////////////////// Mybatis 설정 ////////////////////
	
	// Mybatis : Java 애플리케이션에서 SQL을 더 쉽게 사용할 수 있도록 도와주는 영속성 프레임워크
	// 영속성 프레임워크(Persistence Framework)는 애플리케이션의 데이터를
	// 데이터베이스(DB)와 같은 저장소에 영구적으로 저장하고,
	// 이를 쉽게 조회, 수정, 삭제 등 할 수 있도록 도와주는 프레임워크

	// SqlSessionFactory : SqlSession을 만드는 객체
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception{
									// 매개변수로 DataSource를 받아와 DB 연결 정보를 사용할 수 있도록 함
		
		// MyBatis의 SQL 세션을 생성하는 역할을 할 객체 생성
		// MyBatis에서 이용해야 되는 템플릿을 생성하게 해주는 공장(sessionFactoryBean) 만듦!
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(dataSource);
		
		// sessionFactoryBean이라는 공장에 Mybatis를 이용하기 위한 각종 세팅을 함..
		
		// 세팅 1. mapper.xml(SQL 작성해둘 파일) 파일이 모이는 경로 지정
		// 		 -> Mybatis 코드 수행 시 mapper.xml 을 읽을 수 있음!
		// 매퍼 파일이 모여있는 경로 지정
		sessionFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/**.xml"));
			// 현재 프로젝트  .자원을 얻어오겠다 이 경로( src/main/resources/mappers/하위의 모든 .xml 파일(**.xml))에서!

		// 세팅 2. 해당 패키지 내 모든 클래스의 별칭을 등록
		// - Mybatis는 특정 클래스 지정 시 패키지명.클래스명을 모두 작성해야 함.
		// 	 -> 긴 이름을 짧게 부를 수 있도록 별칭 설정할 수 있음
		
		// 별칭을 지정해야하는 DTO가 모여있는 패키지 지정
		// -> 해당 패키지에 있는 모든 클래스가 클래스명으로 별칭이 지정됨
		// sessionFactoryBean.setTypeAliasesPackage("edu.kh.project.member.model.dto"); --> 이건 너무 길어서
		
		// setTypeAliasesPackage("패키지") 이용 시
		// 클래스 파일명이 별칭으로 등록
		sessionFactoryBean.setTypeAliasesPackage("edu.kh.todo"); // 이렇게 별칭 지정!
		// edu.kh.todo 안에 있는 모든 클래스를 별칭 지정해서 클래스명(Todo)만 입력해도 Mybatis 이용 가능!
		// ex) 원본 edu.kh.todo.model.dto.Todo --> Todo (별칭 등록)
		
		
		// 세팅 3. 마이바티스 설정 파일 경로 지정
		sessionFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/mybatis-config.xml"));
				// 현재 프로젝트  .자원을 얻어오겠다. 이 경로(src/main/resources/mybatis-config.xml)에서!
		
		// SqlSession 객체 반환
		return sessionFactoryBean.getObject();
	}

	// SqlSessionTemplate : 기본 SQL 실행 + 트랜잭션 처리
	// Connection + DBCP + Mybatis + 트랜잭션 처리
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory); // 공장 설비 이용해서 새로운 session 템플릿 생성 후 Bean으로 등록
	}

	// DataSourceTransactionManager : 트랜잭션 매니저
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
