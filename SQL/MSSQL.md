```SQL
SET NOCOUNT ON
```

- SQL문을 저장 프로시저 등에서 실행하면 처리 건수를 클라이언트로 표시하거나 전달되어 예기치 않는 오류가 발생될 수 있으므로, **[SET NOCOUNT ON]** 옵션을 통해 적용 건수 등의 불필요한 데이터가 리턴되지 않게 함



```SQL
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
```

- 다른 사용자가 특정 데이터를 수정하고 있는 상태에서 다른 사용자가 SELECT명령을 수행하면 해당 트랜잭션이 완료될 때까지 **대기현상이 발생**되어 **시스템의 속도가 매우 느리거나 오류가 발생**되기도 함.

- 이러한 문제를 해결하기 위해 다른 사용자가 **변경 중인 데이터를 포함하여 대기없이 읽을 수 있도록** 하는 설정.

- **데이터의 신뢰성은 떨어지지만 대기없이 빠른 속도를 기대**할 수 있어 매우 신뢰성 있고 정확한 보고서 데이터가 아니라면 많이 사용



```sql
SET LOCK_TIMEOUT 5000
```

- 다른 사용자가 이미 특정 데이터를 변경하는 과정에서 장시간 LOCK이 설정 되어 있는 경우 특정 시간동안 **기다리다가** 그래도 **LOCK이 해제되지 않아 더 이상 진행되지 않았을 경우 오류를 발생**시키는 설정



```sql
SET ANSI_WARNINGS OFF
SET ARITHIGNORE ON
SET ARITHABORT OFF
```

- 수식을 계산하면서 **0으로 값을 나눌 경우 오류가 발생**되는 프로시저가 중단되는 문제를 해결하기 위한 방법
- 0으로 나누면 **NULL값으로 결과가 리턴**되기 때문에 **ISNULL() 함수를 함께 사용하는 것을 권장**



```sql
WITH 임시테이블 AS (
	사용할 쿼리
	UNION ALL
	재귀 쿼리
)
SELECT *
FROM 임시테이블 OPTION (MAXRECURSION 재귀호출 최대 실행횟수)
```

- SQL 재귀호출 사용법
- WHILE문으로 수행하는 방법 보다 훨씬 **빠른 속도를 기대**할 수 있음
- **[OPTION (MAXRECURSION 횟수)]** - 시스템에서 반복할 수 있는 최대 횟수 지정



### 커서

- 테이블에서 여러 개의 행을 쿼리한 후에, 쿼리의 결과인 행 집합을 한 행씩 처리하기 위한 방식

커서의 처리 순서

> 1.  커서의 선언 (DECLARE)
>
> 2.  커서 열기 (OPEN)
>
> 3.  커서에서 데이터 가져오기 (FETCH)
>
> 4.  데이터 처리
>
>    > 3, 4 -> WHILE 문으로 모든 행이 처리될 때 까지 반복
>
> 5. 커서 닫기 (CLOSE)
>
> 6. 커서의 해제 (DEALLOCATE)



### PIVOT 함수

- 행을 열로 변환



### UNION / UNION ALL

- UNION은 두 쿼리의 결과를 행으로 합치는 것

  ```sql
  SELECT 문장1
  	UNION [ALL]
  SELECT 문장2
  ```

> UNION = 중복된 열은 제거되고 데이터가 정렬됨
>
> UNION ALL = 중복된 열까지 모두 출력 



### CROSS JOIN

- 한쪽 테이블의 모든 행과 다른 쪽 테이블의 **모든 행을 조인**
- CROSS JOIN의 결과 개수는 **두 테이블 개수를 곱한 값**



### SELF JOIN

- 자기 자신과 자기 자신이 조인

```sql
SELECT 문장1
FROM [테이블1] A
	INNER JOIN [테이블1] B
		ON [조인될 조건]
WHERE [검색조건]
```



### EXCEPT

- 첫 번째 쿼리의 결과 중에서, 두 번째 쿼리에 해당하는 것을 제외

```sql
SELECT 문장1
	EXCEPT
SELECT 문장2
```



### CROSS APPLY(*MSSQL에서만 존재)

- 내부 테이블의 집합으로부터 조인 키로 결합한 **외부 테이블의 행만을 반환**
- INNER JOIN과 동일한 결과 반환
- 한쪽이 다른 쪽에 있는 테이블의 각 행에 대해 **평가하려는 테이블 평가식이 있을 때** 사용하기 좋음

> INNER JOIN -> 전체 데이터에서 AND로 해당 데이터를 걸러냄
>
> CROSS APPLY -> 선택한 데이터만 검색하여 JOIN



### ROLLUP / CUBE

```sql
SELECT A, B
FROM [TABLE]
GROUP BY ROLLUP(A, B)
```

- 컬럼(A, B)에 따른 **소계를 자동 계산하여 테이블 중간에 출력**해줌
- 전체 **합계를 자동 계산하여 결과테이블 가장 아래에 출력**해줌



```sql
SELECT A, B
FROM [TABLE]
GROUP BY CUBE(A, B)
```

- 컬럼(A, B)에 따른 **소계를 자동계산하여 결과테이블 중간에 출력**해줌
- 전체 **합계를 자동 계산하여 결과테이블 가장 윗 행에 출력**해줌
- 컬럼 **A별 소계, B별 소계를 자동 계산하여 결과 테이블 가장 윗 행에 출력**해줌



### CASE / IIF

```sql
-- CASE
SELECT CASE WHEN @X = 'TRUE' THEN 1 ELSE 0 END

-- IIF
SELECT IIF(@X = 'TRUE', 1, 0)

-- IIF의 형식
IIF(BOOLEAN_EXPRESSION, TRUE_VALUE, FALSE_VALUE) 
```

- **여러개의 조건**이 있다면 **CASE** 사용
- **단순 비교문**이라면 **IIF**
- IIF = Boolean 식에 의하여 **참인지 거짓인지에 따라 두 개의 값 중 하나를 리턴**