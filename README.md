## mobileHW1

# 1. 첫번째 화면 (Relative Layout 사용)
- 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button)
- 첫번째 화면 초기화시에 파일에서 개인정보 읽어 오기 --> 로그인 이후 화면에서 서버에서 데이터를 받아옵니다.
- ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
- ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동 

# 2. 두번째 화면 (Linear Layout 사용)
- 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력
- ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크)
- 이름/전화번호/주소(EditView)
- 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept)
- 회원정보는 서버에 저장했습니다.

# 3. 세번째 화면 (Constraint Layout, Table Layout, Grid Layout, Frame Layout 중 하나 사용)
- 첫번째 페이지에서 ID, 비밀번호 입력 시 정상이고 로그인 버튼 클릭 시 화면 출력
- 세번째 화면을 간단한 기능을 수행하도록 구성 ----> 할일을 저장하는 메모장 구현.
- View을 상속한 여러가지 위젯을 사용하여 화면을 구성(기능에 맞는 위젯 선택하여 구성)
  View Group을 상속한 위젯 ListView, AdapterView
  Text View을 상속한 CheckBox, Switch 
  ImageView, ImageButton 등
  
  
  [테스트 환경]
1. 안드로이드 스튜디오 SDK (Tools -> SDK Manager로 확인)
   - SDK 플랫폼 : 9.0(Pie)
   - minSdkVersion 23
   - targetSdkVersion 28
   - compileSdkVersion 28
   - buildToolsVersion "29.0.2"
   - Intel x86 Emulator Accelerator (HXAM Installer)
   
2. JDK-10.0.2 (명령어창(cmd)에서 c:\ java -version으로 확인)
   - java version "1.8.0_201"
   - Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
   - Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

  
3. 안드로이드 스튜디오 실행 환경
   - Windows 10 Home, 64비트 운영체제(x64 기반 프로세서)
     
