# 8 컬렉션 API 개선

### 컬렉션 팩토리

**리스트 팩토리**

- 리스트는 변경 불가
- set 메서드로도 리스트 변경 불가
- 데이터 처리 형식을 설정하거나 데이터를 변환할 필요가 없다면 팩토리 메서드 이용 권장.
    - 팩토리 메서드 구현이 더 단순하고 목적 달성에 충분


**집합 팩토리**

- 변경 불가능한 집합


중복된 요소를 제공해 집합을 만들려고 하면 Olivia라는 요소가 중복되어 있다는 설명과 함께 IlleafalArgumentException이 발생한다.

집합은 오직 고유의 요소막 포함할 수 있다는 원칙

**맵 팩토리**

맵을 초기화 하는 방법 두 가지

```java
// 1. 키와 값을 번갈아 제공하는 방법 - 열 개 이하의 키와 값 쌍을 가진 작은 맵을 만들 때 유용

Map<String,Integer>ageOfFriedns=Map.of("Raphael",30,"Olivai",25,"Thibaut",26);
        System.out.println(ageOfFriends);<-Olivia=25,Raphael=30,Thibaut=26

// 2. Map.ofEntries 팩토리 메서드 이용 - Map.Entry<K, V> 객체를 인수로 받으며 가변 인수로 구현
import static java.util.Map.entry;

Map<String, Integer> ageOfFriedns=Map.ofEntries(
        entry("Raphael",30),
        entry("Olivia",25),
        entry("Thibaut",26));
```

###리스트와 집합 처리

**removeIf**
- 프레디케이트를 만족하는 요소를 제거
- List 나 Set을 구현하거나 그 구현을 상속받은 모든 클래스에서 이용 가능

**replaceAll**
- 리스트의 각 요소를 새로운 요소로 변경

###맵 처리

**forEach 메서드**

- Map.Entry<K, V>의 반복자를 이용, 맵의 항목 집합 반복 가능
- BiConsumer(키와 값을 인수로 받음)를 인수로 받음

```java
ageOfFriends.forEach((friend, age) -> 
        System.out.println(friend + "is" +  age + "years old"));
```

**정렬 메서드**

- Entry.comparingByValue (맵의 항목을 값 기준으로 정렬)
- Entry.comparingByKey (맵의 항목을 키 기준으로 정렬)

```java
Map<String, String> favouriteMovies = Map.ofEntries(
        entry("Raphael", "Star Wars"),
        entry("Cristina", "Matrix"),
        entry("Olivia", "James Bond")
        );

Map<String, String> favouriteMovies
        .entrySet()
        .steram()
        .sorted(Entry.comparingByKey())
        // 사람의 이름을 알파벳 순으로 스트림 요소 처리
        .forEachOrdered(System.out::println);

// 결과
Cristina=Matrix
Olivia=James Bond
Raphael=Star Wars
```

**getOrDefault 메서드**

- 기본값을 반환하는 방식
- 첫 번째 인수로 키를, 두 번째 인수로 기본값을 받으며 맵에 키가 존재하지 않으면 두 번째 인수로 받은 기본값을 반환
- 키가 존재하더라도 값이 널 인 상황에서는 널을 반환
- 키가 존재하느냐의 여부에 따라 두 번째 인수가 반환될지 결정됨

```java
Map<String, String> favouriteMovies = Map.ofEntries(
        entry("Raphael", "Star Wars"),
        entry("Olivia", "James Bond")
        );

// James Bond 출력
System.out.println(favouriteMovies.getOrDefault("Olivia", "Matrix"));
// Matrix 출력
System.out.println(favouriteMovies.getOrDefault("Thibaut", "Matrix"));
```

**계산 패턴**

- computeIfAbsent
    - 제공된 키에 해당하는 값이 없으면(값이 없거나 널) 키를 이용해 새 값을 계산하고 맵에 추가
- computeIfPresent
    - 제공된 키가 존재하면 새 값을 계산하고 맵에 추가한다.
- compute
    - 제공된 키로 새 값을 계산하고 맵에 저장

**교체 패턴**

- replaceAll
    - BiFunction을 적용한 결과로 각 항목의 값을 교체
    - List의 replaceAll과 비슷한 동작 수행
- Replace
    - 키가 존재하면 맵의 값을 변경
    - 키가 특정 값으로 매핑되었을 때만 값을 변경하는 오버로드 버전도 존재

>BiFunction<T, U, R>
> 
> 매개변수가 두 개인 함수형 인터페이스
> 
> T = 함수에 대한 첫 번째 인수의 유형
> 
> U = 함수에 대한 두 번째 인수의 유형
> 
> R = 함수의 결과 유형

```java
// replaceAll을 적용할 것이므로 변경가능한 맵을 사용
Map<String, String> favouriteMovie = new HashMap<>();
favouriteMovie.put("Raphael", "Star Wars");
favouriteMovie.put("Olivai", "james bond");
favouriteMovie.replaceAll((frien, movie) -> movie.toUpperCase());
System.out.println(favouriteMovie); // Olivia=James Bond, Raphael=STAR WARS
```

**합침**

- 두 개의 맵에서 값을 합치거나 바꿔야할 때 merge 메서드 사용
- putAll
    - 두 그룹의 연락처를 포함하는 두 개의 맵을 합칠 때 사용
- merge
    - 값을 좀 더 유연하게 합쳐야 할 때 사용
    - BiFunction을 인수로 받아 중복된 키를 어떻게 합칠지 결정