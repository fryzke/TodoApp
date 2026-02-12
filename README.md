# API 명세
- BASE URL : "/todos"
- Content-Type: application/json
## 1. 일정생성
- Endpoint : POST /todos
- Request Body
```
{
  "name": "Spring 공부하기",
  "content": "JPA Auditing과 API 명세서 작성법 익히기",
  "author": "김르탄",
  "password": "password123"
}
```
- Success Response: 201 Created
- Resopnse Body
```
{
  "id": 1,
  "name": "Spring 공부하기",
  "content": "JPA Auditing과 API 명세서 작성법 익히기",
  "author": "김르탄",
  "createDate": "2026-02-02T14:20:00",
  "editDate": "2026-02-02T14:20:00"
}
```
## 2. 일정 전체 조회
- Endpoint : GET /todos/{author}
- query parameter : author(optional) : 특정 작성자의 일정만 필터
- Success Response: 200 OK
- Error Response: 404 Not Found(없는 일정일 경우)
- Resopnse Body
```
[
  {
    "id": 2,
    "name": "수정된 일정",
    "author": "이르탄",
    "createDate": "2026-02-01T10:00:00",
    "editDate": "2026-02-02T15:00:00"
  },
  {
    "id": 1,
    "name": "Spring 공부하기",
    "author": "김르탄",
    "createDate": "2026-02-02T14:20:00",
    "editDate": "2026-02-02T14:20:00"
  }
]
```
## 3. 일정 단건 조회
- Endpoint : GET /todos/{id}
- query parameter : id : 특정 id의 일정만 필터
- Success Response: 200 OK
- Error Response: 404 Not Found(없는 일정일 경우)
- Resopnse Body
```
  {
    "id": 1,
    "name": "Spring 공부하기",
    "author": "이르탄",
    "createDate": "2026-02-02T14:20:00",
    "editDate": "2026-02-02T14:20:00"
  }
```
## 3. 일정 수정
- Endpoint : PUT /todos/{id}
- query parameter : id : 수정할 일정의 id
- Request Body
```
{
  "name": "수정할 제목",
  "author": "수정할 작성자",
  "password": "기존 비밀번호"
}
```
- Success Response: 200 OK
- Error Response: 404 Not Found(없는 일정일 경우) 400 Bad Request(비밀번호 불일치)
- Resopnse Body
```
  {
    "id": 1,
    "name": "수정할 제목",
    "author": "수정할 작성자",
    "createDate": "2026-02-02T14:20:00",
    "editDate": "2026-02-02T14:20:00"
  }
```
## 4. 일정 삭제
- Endpoint : DELETE /todos/{id}
- query parameter : id : 삭할 일정의 id
- Request Body
```
{
  "password": "기존 비밀번호"
}
```
- Success Response: 204 No Content
- Error Response: 404 Not Found(없는 일정일 경우) 400 Bad Request(비밀번호 불일치)

# ERD
<img width="662" height="392" alt="image" src="https://github.com/user-attachments/assets/dd8f2b22-aa9c-44b5-8245-e52f91902c06" />

