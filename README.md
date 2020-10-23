# 먹방 MUKBANG

## 1. 기획내용

* 전국의 맛집을 한 곳에 모았다. 맛집 어플리케이션 먹방 MUKBANG.

*  맛집 리스트를 통해 한 눈에 맛집을 확인할 수 있다.

* 지도 상에 내 주변 맛집이 띄워져 신속하게 맛집을 확인할 수 있다.



## 시스템 구성도

![system structure](md-images/system%20structure.JPG)

## 구현기술 및 내용

* Servlet과 Network 기능을 이용한 로그인 기능

* Fragment 기능을 이용한 화면 분할 및 전환

* 진동, 소리 및 상단 알림 기능

* JSON을 이용한 서버에 데이터 요청 및 ListView를 이용한 맛집 데이터 목록 구현

* FCM을 이용한 푸시 서비스기능

* Google Map을 이용한 지도 구현 및 Location 기능

* Thread 를 이용한 이미지 불러오기



##  구현 결과 화면

### 1. 홈

![홈화면](md-images/%ED%99%88%ED%99%94%EB%A9%B4.JPG)

### 2. 맛집 리스트

![맛집리스트 화면](md-images/%EB%A7%9B%EC%A7%91%EB%A6%AC%EC%8A%A4%ED%8A%B8%20%ED%99%94%EB%A9%B4.JPG)

### 3. 내 주변 맛집

![내주변 맛집 화면](md-images/%EB%82%B4%EC%A3%BC%EB%B3%80%20%EB%A7%9B%EC%A7%91%20%ED%99%94%EB%A9%B4.JPG)