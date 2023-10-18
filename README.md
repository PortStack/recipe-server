# recipe-server
레시피 앱 : https://github.com/PortStack/PortStack_Android

## 사용 기술
* 언어 : Java 17
* 프레임워크 : Spring boot, Spring Security, Spring Mvc,JPA
* DB : MariaDB 11.0.2
* Server : AWS EC2

## DB ERD
![erd](https://github.com/PortStack/recipe-server/assets/69244467/ccd0daae-7807-43d2-9ae1-8f235d3e0846)


## 폴더 구조
```bash
recipe
 ┣ Controller
 ┃ ┣ BoardCommentController.java
 ┃ ┣ BoardController.java
 ┃ ┣ CommentController.java
 ┃ ┣ ImageCacheController.java
 ┃ ┣ MainController.java
 ┃ ┣ RecipeController.java
 ┃ ┣ UserController.java
 ┃ ┗ home.java
 ┣ Dto
 ┃ ┣ AuthDto.java
 ┃ ┣ BoardCommentDto.java
 ┃ ┣ BoardDto.java
 ┃ ┣ CommentDto.java
 ┃ ┣ CookOrderDto.java
 ┃ ┣ RecipeDto.java
 ┃ ┣ RecipeIngredientDto.java
 ┃ ┣ RefreshTokenDto.java
 ┃ ┣ ResponseSingDto.java
 ┃ ┣ TagDto.java
 ┃ ┗ UserDto.java
 ┣ config
 ┃ ┣ SecurityConfig.java
 ┃ ┗ SpringConfig.java
 ┣ domain
 ┃ ┣ recipe
 ┃ ┃ ┣ CommentEntity.java
 ┃ ┃ ┣ CookOrder.java
 ┃ ┃ ┣ CookOrderImage.java
 ┃ ┃ ┣ Ingredient.java
 ┃ ┃ ┣ RecipeEntity.java
 ┃ ┃ ┣ RecipeIngredientEntity.java
 ┃ ┃ ┣ RecipeLikeEntity.java
 ┃ ┃ ┣ RecipeTagMap.java
 ┃ ┃ ┣ Tag.java
 ┃ ┃ ┗ ThumbNailEntity.java
 ┃ ┣ Authority.java
 ┃ ┣ Board.java
 ┃ ┣ BoardCommentEntity.java
 ┃ ┣ BoardEntity.java
 ┃ ┣ BoardLikeEntity.java
 ┃ ┣ Image.java
 ┃ ┣ Recipe.java
 ┃ ┣ RefreshTokenEntity.java
 ┃ ┣ SearchEntity.java
 ┃ ┣ TimeEntity.java
 ┃ ┣ User.java
 ┃ ┗ UserEntity.java
 ┣ global
 ┃ ┣ Dto
 ┃ ┃ ┣ BaseResponse.java
 ┃ ┃ ┣ CustomUserDetails.java
 ┃ ┃ ┣ ListDataResponse.java
 ┃ ┃ ┗ SingleDataResponse.java
 ┃ ┣ Service
 ┃ ┃ ┣ JpaUserDetailsService.java
 ┃ ┃ ┗ ResponseService.java
 ┃ ┣ exception
 ┃ ┃ ┗ LoginFailedException.java
 ┃ ┣ filter
 ┃ ┃ ┗ CustomAuthenticationFilter.java
 ┃ ┣ jwt
 ┃ ┃ ┣ JwtAuthenticationFilter.java
 ┃ ┃ ┗ JwtProvider.java
 ┃ ┗ modules
 ┃ ┃ ┣ BoardLikeComparator.java
 ┃ ┃ ┣ CurrentDateTime.java
 ┃ ┃ ┣ FileHandler.java
 ┃ ┃ ┣ PageInfo.java
 ┃ ┃ ┗ RecipeLikeComparator.java
 ┣ repository
 ┃ ┣ AuthRepository.java
 ┃ ┣ BoardCommentRepository.java
 ┃ ┣ BoardLikeRepository.java
 ┃ ┣ BoardRepository.java
 ┃ ┣ CommentRepository.java
 ┃ ┣ CookOrderImageRepository.java
 ┃ ┣ CookOrderRepository.java
 ┃ ┣ IngredientRepository.java
 ┃ ┣ MemoryBoardRepository.java
 ┃ ┣ MemoryUserRepository.java
 ┃ ┣ RecipeIngredientRepository.java
 ┃ ┣ RecipeLikeRepository.java
 ┃ ┣ RecipeRepository.java
 ┃ ┣ RefreshTokenRepository.java
 ┃ ┣ SearchRepository.java
 ┃ ┣ TagMapRepository.java
 ┃ ┣ TagRepository.java
 ┃ ┣ ThemNailRepository.java
 ┃ ┗ UserRepository.java
 ┣ service
 ┃ ┣ AuthService.java
 ┃ ┣ BoardCommentService.java
 ┃ ┣ BoardService.java
 ┃ ┣ CommentService.java
 ┃ ┣ CookOrderService.java
 ┃ ┣ RecipeService.java
 ┃ ┣ RefreshTokenService.java
 ┃ ┣ TagService.java
 ┃ ┗ UserServiceExample.java
 ┗ RecipeApplication.java
```
