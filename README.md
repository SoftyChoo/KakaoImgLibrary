<br/>

![header](https://capsule-render.vercel.app/api?type=cylinder&color=0:fffc00,100:ffffff&height=230&section=header&text=Kakao%20Img%20Library&fontColor=3e2723&fontSize=70&animation=fadeIn&fontAlignY=50&desc=MVVM%20Architecture&descAlignY=70)

<br/>

# 📌 Project
이 프로젝트는 카카오뱅크 과제를 모티브로 제작한 개인 프로젝트입니다.<br/>
유저는 검색을 통해 이미지와 영상을 북마크에 추가할 수 있습니다.

<br/>

# ⚙️ Architecture
- ViewModel, LiveData, Repository을 사용한 MVVM 구조
- 유지 보수성과 확장성을 높이기 위해 View와 Business Logic을 분리 하였습니다.

<br/>

# 🎨 Style & Logo
- 디테일과 완성도를 위해 실제 카카오 어플리케이션에서 사용하는 아이콘과 색상을 참조해서 만들었습니다.

<img src = "https://github.com/SoftyChoo/KakaoImgLibrary/assets/132810978/32aa3e8a-30c1-4df2-8de5-bf793a4c2ef3" height = "200"><img height="200" alt="image" src="https://github.com/SoftyChoo/KakaoImgLibrary/assets/132810978/94019312-db92-4592-8110-3a6243324ea7"><img height="200" alt="image" src="https://github.com/SoftyChoo/KakaoImgLibrary/assets/132810978/bb92bd75-fbf6-497c-96ca-4d6433c91ae1">


<br/>

# 💡 Tech
**Android Jetpack**
- Data Binding
- LiveData
- ViewModel

**Third Party Libraries**
- Retrofit2
- Glide
- Kotlin Coroutine

**UI Frameworks and Components**
- ConstraintLayout
- Material Design
- RecyclerView
- Fragment
- ViewPager2
- TabLayout
- SearchView

**Data Source** 
- Search data retrieved using  [Kakao Developers Open API](https://developers.kakao.com/product/search)

<br/>

# File Structure
- `common/`
    - `utils/`
        - `CommonUtils.kt` : 프로젝트 전반에 사용되는 유틸리티 함수 모음
    - `Constans` : 프로젝트 전반에 사용되는 상수 값 모음
    - `EntryType` : 프로젝트 전반에 사용되는 진입타입 결정
    - `SharedViewModel.kt` : Fragment간 공유되는 데이터를 관리
- `data/`
    - `api/`
        - `Retrofit` : Retrofit을 통한 API 호출 인터페이스 정의
    - `model/`
        - `ImageSearchResponse` : 이미지 정보를 받아오는 데이터 모델
        - `VideoSearchResponse` : 영상 정보를 받아오는 데이터 모델
    - `Repository` :
    - `RetrofitClient` : Retrofit 인스턴스 및 설정 관련 로직
- `main/`
    - `MainActivity` : 앱의 메인 액티비티
    - `MainTabs` : 메인 탭 관리
    - `ViewPagerAdapter` : 뷰 페이저 어댑터
- `model/`
    - `BookmarkModel.kt`: 북마크 데이터 모델
    - `SearchModel.kt`: 검색 데이터 모델
- `ui/` : 애플리케이션의 UI를 담당하는 Fragment 및 Adapter
    - `bookmark/`
        - `BookmarkFramgment` : 북마크 화면 UI 및 로직 처리
        - `BookmarkListAdapter` : 북마크 화면에서 사용하는 리사이클러뷰 어댑터
    - `search/`
        - `SearchFragment` : 검색 화면 UI 및 로직 처리
        - `SearchListAdapter` : 검색 화면에서 사용하는 리사이클러뷰 어댑터
- `viewmodel/`: MVVM의 ViewModel을 포함하며, UI 로직과 데이터의 중개 역할을 합니다. LiveData를 사용하여 UI의 상태 및 데이터 변경을 관찰합니다. 비즈니스 로직이 구현되어 있습니다.
    - `bookmark/`
        - `BookmarkViewModel` : 북마크 화면의 데이터 및 로직 처리를 담당
        - `BookmarkViewModel` : BookmarkViewModel 생성을 위한 Factory 클래스
    - `search/`
        - `SearchViewModel`: 검색 화면의 데이터 및 로직 처리를 담당
        - `SearchViewModelFactory`: SearchViewModel 생성을 위한 Factory 클래스
    
<br/>
