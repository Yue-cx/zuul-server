# Condition 部分

## 基础信息
- **Base URL**: `/api/condition`        
- **Content-Type**: `application/json`
- **响应格式**: JSON

## API 列表

### 1. 获取用户背包物品

**描述**: 根据用户ID获取该用户的背包物品列表

**请求**:
- **方法**: GET
- **路径**: `/bag/{id}`
- **路径参数**:
  - `id`: 用户ID (整数)

**响应**:
- **成功响应** (200 OK):
  ```json
  [
    {
      "id": 1,
      "name": "剑",
      "description": "锋利的武器",
      "weight": 2.5,
      "isUsable": true
    },
    {
      "id": 2,
      "name": "药水",
      "description": "恢复生命值",
      "weight": 0.5,
      "isUsable": true
    }
  ]
  ```

**示例请求**:
```
GET /api/condition/bag/5
```

### 2. 保存用户背包物品

**描述**: 保存用户的背包物品数据到数据库

**请求**:
- **方法**: POST
- **路径**: `/save/backpack`
- **请求体**:
  ```typescript
  {
    userId: number,       // 用户ID
    inventory: Item[]     // 物品数组
  }
  ```
  其中Item结构为:
  ```typescript
  {
    id: number,           // 物品ID
    name: string,         // 物品名称
    description: string,  // 物品描述
    weight: number,       // 物品重量
    isUsable: boolean     // 是否可用
  }
  ```

**响应**:
- **成功响应** (200 OK):
  ```json
  "成功保存X条背包物品数据"
  ```

**示例请求**:
```json
POST /api/condition/save/backpack
{
  "userId": 5,
  "inventory": [
    {
      "id": 1,
      "name": "剑",
      "description": "锋利的武器",
      "weight": 2.5,
      "isUsable": true
    },
    {
      "id": 2,
      "name": "药水",
      "description": "恢复生命值",
      "weight": 0.5,
      "isUsable": true
    }
  ]
}
```

### 3. 保存用户房间物品

**描述**: 保存用户房间物品数据到数据库

**请求**:
- **方法**: POST
- **路径**: `/save/rooms`
- **请求体**:
  ```typescript
  {
    userId: number,       // 用户ID
    rooms: Room[]         // 房间数组
  }
  ```
  其中Room结构应包含:
  ```typescript
  {
    id: number,           // 房间ID
    name: string,         // 房间名称
    description: string,  // 房间描述
    items: Item[]         // 房间内物品数组
  }
  ```

**响应**:
- **成功响应** (200 OK):
  ```json
  "成功保存X条房间物品数据"
  ```

**示例请求**:
```json
POST /api/condition/save/rooms
{
  "userId": 5,
  "rooms": [
    {
      "id": 1,
      "name": "大厅",
      "description": "城堡的大厅",
      "items": [
        {
          "id": 3,
          "name": "椅子",
          "description": "木制椅子",
          "weight": 5.0,
          "isUsable": false
        }
      ]
    }
  ]
}
```





# RoomController API 文档

## 基础信息
- **Base URL**: `/api/room`
- **Content-Type**: `application/json`
- **响应格式**: JSON

## API 列表

### 1. 获取指定ID的房间信息

**描述**: 根据房间ID获取单个房间的详细信息

**请求**:
- **方法**: GET
- **路径**: `/{id}`
- **路径参数**:
  - `id`: 房间ID (整数)

**响应**:
- **成功响应** (200 OK):
  ```json
  {
    "id": 1,
    "name": "大厅",
    "description": "城堡的中央大厅",
    "exits": {
      "north": 2,
      "east": 3
    },
    "items": [
      {
        "id": 1,
        "name": "椅子",
        "description": "木制椅子",
        "weight": 5.0,
        "isUsable": false
      }
    ]
  }
  ```

**示例请求**:
```
GET /api/room/1
```

### 2. 获取所有房间信息

**描述**: 获取系统中所有房间的列表

**请求**:
- **方法**: GET
- **路径**: `/`

**响应**:
- **成功响应** (200 OK):
  ```json
  [
    {
      "id": 1,
      "name": "大厅",
      "description": "城堡的中央大厅",
      "exits": {},
      "items": []
    },
    {
      "id": 2,
      "name": "卧室",
      "description": "主人的卧室",
      "exits": {},
      "items": []
    }
  ]
  ```

**示例请求**:
```
GET /api/room
```

### 3. 获取用户关联的所有房间

**描述**: 获取与特定用户关联的所有房间信息(用户曾经存档的房间)

​	 若从未存档,返回初始房间信息

**请求**:
- **方法**: GET
- **路径**: `/user/{id}`
- **路径参数**:
  - `id`: 用户ID (整数)

**响应**:
- **成功响应** (200 OK):
  ```json
  [
    {
      "id": 1,
      "name": "大厅",
      "description": "城堡的中央大厅",
      "exits": {
        "north": 2,
        "east": 3,
        "west": 4
      },
      "items": [
        {
          "id": 1,
          "name": "椅子",
          "description": "木制椅子，看起来有些年头了",
          "weight": 5.0,
          "isUsable": false
        },
        {
          "id": 2,
          "name": "烛台",
          "description": "铜制烛台，蜡烛已经燃尽",
          "weight": 3.5,
          "isUsable": true
        }
      ]
    }
  ]
  ```

**示例请求**:
```
GET /api/room/user/5
```

## 错误处理
所有API遵循以下错误响应格式:
```json
{
  "timestamp": "2023-04-01T12:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "房间不存在",
  "path": "/api/room/999"
}
```

## 注意事项
1. 所有GET请求无需请求体
2. ID参数必须为有效正整数
3. 用户关联房间API返回的数据包含该用户在房间内的物品信息
4. 基础房间列表API只返回房间基本信息，不包含物品详情

## 响应字段说明
- **Room对象**:
  - `id`: 房间唯一标识
  - `name`: 房间名称
  - `description`: 房间描述
  - `exits`: 出口信息(可选，键值对形式)
  - `items`: 房间内物品列表(可选)
  - `userItems`: 用户在房间内的物品列表(可选)

- **Item对象**:
  - `id`: 物品唯一标识
  - `name`: 物品名称
  - `description`: 物品描述
  - `weight`: 物品重量
  - `isUsable`: 是否可用标志