  #### 1.   Не используем магические числа:
   
   Используем метод коллекций ```size()``` вместо просто числа продуктов, поэтому спокойно можем добавлять новые товары.          
   [ссылка](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L257)
  
  #### 2.   DRY
  Для красивого вывода в консоль использовал разделитель, вызывается он в нескольких методах,
  поэтому вывел его в отдельный метод [printDelim()](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L268)
  
  При выводе в консоль списка всех [товаров](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L28) и отфильтрованного [списка](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L105) использовал отдельный метод
  [printProductList()](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L247) 
  
  Также использовал один и тот же метод  [printProductBasket()](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L197) 
  для вывода списка корзины [здесь](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L137)
  и при выводе подробной информации о сделанном заказе [здесь](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L167)
  
  #### 3  Принцип единственной ответственности (Single-Responsibility principle)
  
  Класс должен выполнять только те функции, для которых он был логически создан.
  
  Прогармма разделена на классы:       
  ```Main``` - в котором происходит общение с покупателем(интерфейс пользователя)       
  ```Buyer``` - покупатель, в нем содержится данные о клиенте и реализованы методы - те действия, которые может выполнить пользователь,
  в частности, найти товар по различным фильтрам, наполнить корзину товарами, сделать заказ, найти заказ по номеру и просмотреть всю историю заказов.
  Тут хранится список товаров в его корзине и список всех его заказов.      
  ```Order```  -  заказ, здесь хранятся данные о заказе ( его номер, дата и время, и список покупок).        
  ```Product``` - товар(продукты), для хранения информации о каждом товаре.
  
  #### 4 Принцип открытости / закрытости (Open-closed principle)      
  Не изменяя исходных классов можно расширить функциональность программы. Для демонстрации 
  создан класс ```vipBuyer``` - VIP-клиент(покупатель), который наследуется от базового класса ```Buyer```, обладает его функциональностью плюс может содержать дополнительные данные и методы.
  
  
  #### 5  Принцип замены Барбары Лисков (Liskov substitution principle)
  
  Здесь как пример наследование ```vipBuyer``` от  ```Buyer``` . Vip тоже покупатель, но с дополнительными привелигерованными функциями. Также можно создать класс ```wholesaleBuyer```(оптовик), наследуемый от ```Buyer```. И оба наследника будут "отыгрывать роль" своего предка. 
  
  ####  6  Принцип инверсии зависимостей (Dependency inversion principle)
  
  Зависьте от абстракций , а не от имплементаций.
  
  [Пример1](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L11) 
  
  В коллекциях
  [Пример2](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Main.java#L273)
  [Пример3](https://github.com/xelarog/JavaHomework-DesignPatterns-SOLID/blob/579492ad60e92b73e9b5f65d10b25e04a273725e/Magazine/src/main/java/Users/Buyer.java#L24)
  
  
  
  
  
  
  
  
  
  
