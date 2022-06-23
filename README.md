  #### 1.   Не используем магические числа:
   
   Используем методы коллекций size() вместо просто числа продуктов, поэтому спокойно можем добавлять новые товары.          
   [ссылка](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L256)
  
  #### 2.   DRY
  Для красивого вывода в консоль использовал разделитель, вызывается он в нескольких методах,
  поэтому вывел его в отдельный метод [printDelim()](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L267)
  
  При выводе в консоль списка всех [товаров](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L27) и отфильтрованного [списка](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L104) использовал отдельный метод
  [printProductList()](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L246) 
  
  Также использовал один и тот же метод  [printProductBasket()](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L196) 
  для вывода списка корзины [здесь](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L136)
  и при выводе подробной информации о сделанном заказе [здесь](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/dbc5ed7622c3fcc33cf659289af0a56120c8fdec/Magazine/src/main/java/Main.java#L166)
  
  #### 3  Принцип единственной ответственности (Single-Responsibility principle)
  
  Класс должен выполнять только те функции, для которых он был логически создан.
  
  Прогармма разделена на классы:       
  ```Main``` - в котором происходит общение с покупателем(интерфейс пользователя)       
  ```Buyer``` - покупатель, в нем содержится данные о клиенте и реализованы методы - те действия, которые может выполнить пользователь,
  в частности, найти товар по различным фильтрам, наполнить корзину товарами, сделать заказ, найти заказ по номеру и просмотреть всю историю заказов.
  Тут хранится список товаров в его корзине и список всех его заказов.      
  ```Order```  -  заказ, здесь хранятся данные о заказе ( его номер, дата и время, и список покупок).        
  ```Product``` - товар(продукты), для хранения информации о каждом товаре.
  
  #### 4 Принцип открытости и закрытости
  
  
  
  
  
