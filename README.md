# KakaoImgLibrary


## 프로그램 설명
이 프로젝트는 이미지 검색 및 북마크 기능을 제공하는 애플리케이션입니다. 사용자는 이미지를 검색하고 원하는 이미지를 북마크에 추가할 수 있습니다.

## 폴더 및 파일 구조
- `data/`: 애플리케이션의 데이터 로직과 API 호출을 처리합니다.
    - `api/`
        - `Retrofit_interface`: Retrofit을 통한 API 호출 인터페이스 정의
    - `model/`
        - `ImageModel`: 이미지 정보를 저장하는 데이터 모델
        - `VideoModel`: 비디오 정보를 저장하는 데이터 모델
    - `Retrofit_client.kt`: Retrofit 인스턴스 및 설정 관련 로직

- `ui/`: 애플리케이션의 UI를 담당하는 Fragment 및 Adapter가 위치합니다.
    - `bookmark/`
        - `BookmarkAdapter`: 북마크 화면에서 사용하는 리사이클러뷰 어댑터
        - `BookmarkFragment`: 북마크 화면 UI 및 로직 처리
    - `search/`
        - `SearchAdapter`: 검색 화면에서 사용하는 리사이클러뷰 어댑터
        - `SearchFragment`: 이미지 검색 화면 UI 및 로직 처리

- `utils/`
    - `Utils`: 프로젝트 전반에 사용되는 유틸리티 함수 모음

- `viewmodel/`: MVVM의 ViewModel을 포함하며, UI 로직과 데이터의 중개 역할을 합니다. LiveData를 사용하여 UI의 상태 및 데이터 변경을 관찰합니다. 비즈니스 로직이 구현되어 있습니다.
    - `bookmark/`
        - `BookmarkViewModel`: 북마크 화면의 데이터 및 로직 처리를 담당
    - `search/`
        - `SearchViewModel`: 검색 화면의 데이터 및 로직 처리를 담당
        - `SearchViewModelFactory`: SearchViewModel 생성을 위한 Factory 클래스
    - `SharedViewModel.kt`: 여러 Fragment 간에 공유되는 데이터를 관리

- `Constants`: 프로젝트 전반에 사용되는 상수 값 모음
- `MainActivity`: 애플리케이션의 메인 엑티비티

## MVVM 패턴
이 프로젝트는 MVVM(Model-View-ViewModel) 디자인 패턴이 적용되었습니다. View와 Business Logic이 분리되어 있어 유지 보수와 확장성이 높아집니다.
