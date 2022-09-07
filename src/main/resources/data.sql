/**
  GUEST INSERT
 */
INSERT INTO guest
values (1, NOW(), NOW(), '1994.12.01', 'a948594@naver.com', true, '강영민', '010-5948-3938', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B0%95%EC%98%81%EB%AF%BC.jpg', 'ACTIVE');
INSERT INTO guest
values (2, NOW(), NOW(), '1985.03.10', 'fdsfs@daum.net', true, '김구라', '010-4343-5435', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B5%AC%EB%9D%BC.png', 'NOT_AUTH');
INSERT INTO guest
values (3, NOW(), NOW(), '2002.05.25', 'fgfg@gmail.com', false, '김주민', '010-9828-3333', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B0%95%EC%98%81%EB%AF%BC.jpg', 'ACTIVE');
INSERT INTO guest
values (4, NOW(), NOW(), '1985.12.25', 'sdkels2@naver.com', true, '여주영', '010-2185-3892', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%8B%A0%EC%A7%80%EC%97%B0.jpg', 'NOT_AUTH');
INSERT INTO guest
values (5, NOW(), NOW(), '2003.11.16', 'a948594@naver.com', false, '정주영', '010-3948-3567', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%8B%A0%EC%A7%80%EC%97%B0.jpg', 'ACTIVE');
INSERT INTO guest
values (6, NOW(), NOW(), '2000.06.19', 'yolu@naver.com', false, '신지연', '010-4343-3323', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%8B%A0%EC%A7%80%EC%97%B0.jpg', 'ACTIVE');
INSERT INTO guest
values (7, NOW(), NOW(), '1999.03.28', 'enime434@naver.com', false, '이유진', '010-1577-1577', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B0%95%EC%98%81%EB%AF%BC.jpg', 'NOT_AUTH');
INSERT INTO guest
values (8, NOW(), NOW(), '1978.03.01', 'nebiros@tistory.com', false, '연준호', '010-8675-4938', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B0%95%EC%98%81%EB%AF%BC.jpg', 'ACTIVE');
INSERT INTO guest
values (9, NOW(), NOW(), '1965.02.26', 'loveyooop@naver.com', true, '정준길', '010-5464-2343', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B0%95%EC%98%81%EB%AF%BC.jpg', 'ACTIVE');
INSERT INTO guest
values (10, NOW(), NOW(), '1999.04.04', 'saymymin@kakao.com', true, '김준호', '010-4664-5453', 25000,
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EC%A4%80%ED%98%B8.jpg', 'NOT_AUTH');

-- /**
-- HOST INSERT
--  */
INSERT INTO host
values (1, NOW(), NOW(), '010-5948-3938', '2013161993',
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EC%88%98.png', 1);

INSERT INTO host
values (2, NOW(), NOW(), '010-4343-5435', '5958474733',
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EB%A3%A1.png', 2);

INSERT INTO host
values (3, NOW(), NOW(), '010-9433-3544', '1952434533',
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%95%88%EC%88%99%ED%9D%AC.jpg', 4);

INSERT INTO host
values (4, NOW(), NOW(), '010-2955-4948', '3837273644',
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%95%84%EC%9D%B4%EC%9C%A0.jpg', 9);

INSERT INTO host
values (5, NOW(), NOW(), '010-3948-4938', '5453442233',
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EC%A0%95%EC%9D%80.jpg', 10);


/**
-- accommodation insert
 */
INSERT INTO accommodation
values (1, NOW(), NOW(), 'pension', '충북 청주시 노원구 삼할동 떙떙번지', '호텔 뷰가 정말 좋은 장소입니다.', '정목호텔',
        '다들 안녕하세요. 정목호텔입니다. 과도한 음주는 삼가해주시길 바랍니다.', '제1조: 호텔을 꺠끗이 쓰도록 한다.', 125, 1);
INSERT INTO accommodation
values (2, NOW(), NOW(), 'pension', '서울 강서구 등촌동 구구호텔', '강서구 최고의 경치!!', '구구호텔', '구구호텔에 오신 것을 환영합니다. 과도한 애정행각은 금지입니다.',
        '쓰고간 곳은 청결히 유지해주세요.', 11, 2);
INSERT INTO accommodation
values (3, NOW(), NOW(), 'motel', '서울 신림구 신림동 신림짝모텔', '둘이 들어가서 셋이되서 나오는 짝 모텔', '신림짝모텔', '신림짝 모텔입니다. 즐거운 사랑을 나누세요.',
        '쓰고간 곳은 청결히 유지해주세요. 위반 시 벌금 1백만원', 25, 2);
INSERT INTO accommodation
values (4, NOW(), NOW(), 'motel', '서울 강남구 강남동 강남 어게인', '밝게 빛나는 강남 어게인', '강남어게인', '강남 어게인 입니다.',
        '1. 뒷처리는 깔끔히 한다. 2. 쓰고남은 휴지는 휴지통에 버린다.', 509, 3);
INSERT INTO accommodation
values (5, NOW(), NOW(), 'guest_house', '제주 특별시 감귤 게스트 하우스', '어서오세요, 감귤 게스트 하우스 입니다.', '감귤 게스트 하우스', '편하게 즐기다 가세요~',
        '놀다간 뒷처리는 깔끔하게~', 119, 4);
INSERT INTO accommodation
values (6, NOW(), NOW(), 'villa', '경남 김해시 진주빌라', '진주 빌라 입니다.', '진주빌라', '어서오세요~ 진주빌라입니다.',
        '1. 뒷처리는 깔끔히 한다. 2. 쓰고남은 휴지는 휴지통에 버린다.', 3, 5);


/**
-- room 정의
 */
-- 1번 호텔 방 정의
INSERT INTO ROOM
values (1, now(), now(), '침실2대 풀사이즈 제공', '평온하고 쓰기 좋은 방이에요.', 2, '라벤더룸', false, 2, 1);

INSERT INTO ROOM
values (2, now(), now(), '넓은 사이즈의 침대, 향수 풀옵션 제공', '한번 들어오면 나가기 싫어지는 방', 2, '풀옵션방', false, 2, 1);

INSERT INTO ROOM
values (3, now(), now(), '침실2대 풀사이즈 제공', '평온하고 쓰기 좋은 방이에요.', 2, '글래스룸', false, 2, 1);

-- 2번 호텔 방 정의
INSERT INTO ROOM
values (4, now(), now(), '침실2대 풀사이즈 2대 제공, 장미향 옵션', '은은한 장미향이 코끝을 맴도는 매혹적인 방', 2, '로즈룸', false, 2, 2);

INSERT INTO ROOM
values (5, now(), now(), '에이스 침대, 해바라기향 옵션', '해바라기 향이 맴도는 머물다 가기 좋은 방이에요', 2, '해바라기방', false, 2, 2);


-- 3번 호텔 방 정의
INSERT INTO ROOM
values (6, now(), now(), '풀옵션 구비', '스탠다드 방입니다. 문의사항이 있으면 안내데스크로 연락 주세요.', 2, '스탠다드룸', false, 2, 3);

INSERT INTO ROOM
values (7, now(), now(), '풀옵션 구비', '라지 룸입니다. 문의사항이 있으면 안내데스크로 연락 주세요.', 2, '라지룸', false, 2, 3);


-- 4번 호텔 방 정의
INSERT INTO ROOM
values (8, now(), now(), '초강력특가-14시 체크아웃', '상세 설명의 취소규정에 따라 취소가 가능하며, 취소를 원하시는 경우 고객센터로 연락 부탁 드립니다.', 2, '스탠다드룸', false,
        2, 4);

INSERT INTO ROOM
values (9, now(), now(), '킹 사이즈 침대 1개
2인 기준 / 최대 3인
3인 투숙 시 엑스트라 베드 추가 ( 추가 요청은 체크인 하루 전날 까지 가능)
엑스트라 베드 요청 시 33,000원 추가 요금 발생 (현장 결제)
부티크 킹 이상 상위객실 친환경 비건제품 프리아 어메니티 및 에비앙 생수 2병 제공
시몬스
에어컨, UHD TV, 냉장고, 생수
샤워시설, 욕실용품, 드라이기, 가운
칫솔, 치약, 슬리퍼 제공
무료 Wi-Fi
금연객실
딜라이브 OTT 셋톱박스 설치. 스트리밍 서비스 자유 이용 가능(넷플릭스 등)
체크인 기준 3일전 17시까지 100% 환불', '상세 설명의 취소규정에 따라 취소가 가능하며, 취소를 원하시는 경우 고객센터로 연락 부탁 드립니다.
12 to 21★DAYUSE 9시간 상품 : 체크인 12:00 이후, 체크아웃 21:00 이전
체크인 15:00 이후, 체크아웃 12:00 이전
전 객실 딜라이브 OTT 셋톱박스 이용 가능(단, 넷플릭스 등 유료 서비스 이용 시, 본인 계정 이용)
유료주차(1대 1박, 10,000원, 기계식주차)
호텔 주차장 만차 시, 인근 유료 주차장(고객 부담)으로 안내 해 드리니 많은 양해 부탁 드립니다.
SUV, RV, 대형 세단, 스포츠카는 주차타워 입고가 불가합니다. (차종에 따라 입고가 불가능할 수 있으므로 사전 확인 필요.)
외부 주차 공간이 매우 협소하므로 대중교통 이용을 부탁드리겠습니다.
부티크 킹 객실 이상 무료 주차
투숙하시는 모든 고객님께 428 레스토랑 10% 할인 제공
[부대 시설]
★1층 428 레스토랑 - 현재 사회적 거리두기 격상으로 운영시간이 변동되었으므로, 자세한 문의는 02-6230-8880 으로 문의 부탁드립니다.
월-금) 런치 11:30-15:00 (L/O 14:30) 디너 18:00-23:00 (L/O 22:00)
토) 런치 11:30-15:00 (L/O 14:30) 디너 18:00-23:00 (L/O 22:00)
일) 런치 11:30-15:00 (L/O 14:30) 디너 18:00-21:00 (L/O 20:00)
★15층 루프탑바 - 현재 임시 휴업
★피트니스센터 / 지하 1층 / 24시간 / 헬스기구. 필라테스실 (투숙객 한정) → 사회적 거리두기로 인해 당분간 운영이 중단 된 점 양해 부탁드립니다.
★11층 라운지는 현재 운영이 중단되어 예약시 참고 부탁드립니다.
★셀프세탁실 무료/ 24시간/세탁세제(유상판매)
★모든 추가비용 현장결제', 2, '파노라믹 스위트', false, 2, 4);


-- 5번 호텔 방 정의
INSERT INTO ROOM
values (10, now(), now(), '인원 추가 정보
- 인원 : 기준 2명 / 최대 3명
- 인원추가 : 가능
- 인원추가요금 : 성인 30,000원, 아동 30,000원, 유아 30,000원
- 12개월 영유아는 무료이며, 그 외에는 추가비를 내셔야 합니다.
객실 정보
거실+방, 복층형 / 30평
구비시설
스파/월풀, 개별바비큐, 침대, 에어컨, TV, 취사시설, 식탁, 냉장고, 전자레인지, 커피포트, 실내개별온수수영장,헤어드라이기 샴푸린스바디워시비누치약 수건
2018년 3월 1일에 오픈한 독채형 신축풀빌라 객실입니다.
실내 개별 온수 수영장을 갖추고 있어 프라이빗한 휴식을 즐기실 수 있습니다.
야외개별데크에서 숯불로 이용하실 경우 2~4인 20,000원 / 4인초과시 30,000원입니다.
입실 후 현장에서 결제부탁드립니다.
* 달의연인에 대해 더 상세한 내용을 확인해보세요
https://blog.naver.com/otmtech/221225799944', '온수는 31도, 온수요금은 별도로 받지 않고 객실요금에 포함입니다.
예약 인원에서 인원이 추가되는 경우 펜션에 미리 연락을 주시기 바랍니다.
최대 인원 초과 시 입실이 불가능할 수 있으며, 해당 사유로 환불 받을 수 없습니다.
반려동물 동반시 입실이 거부되며, 해당 사유로 환불 받을수 없습니다.
미성년자는 숙박이 불가능합니다.
다음 이용 고객을 위해 입실, 퇴실 시간을 준수해 주시기 바랍니다.
객실 및 주변시설 이용 시 시설물의 훼손, 분실에 대한 책임은 투숙객에게 있으며, 손해배상의 책임을 질 수 있습니다.
객실의 안전과 화재예방을 위해 객실 내에서 생선이나 고기 등을 굽는 직화 방식은 허용되지 않으며, 개인적으로 준비해 오는 취사도구(그릴, 숯, 전기/전열기구 등)은 반입이 금지되어 있습니다.
객실 내에서의 흡연은 금지되어 있으며, 지정된 장소를 이용해 주시기 바랍니다.
다른 이용객에게 피해를 줄 수 있는 무분별한 오락, 음주, 고성방가는 삼가주시기 바랍니다.', 6, '달의연인', false, 4, 5);

INSERT INTO ROOM
values (11, now(), now(), '인원 추가 정보
- 인원 : 기준 2명 / 최대 4명
- 인원추가 : 가능
- 인원추가요금 : 성인 30,000원, 아동 30,000원, 유아 30,000원
- 12개월 영유아는 무료이며, 그 외에는 추가비를 내셔야 합니다.
객실 정보
거실+방, 복층형 / 30평
구비시설
스파/월풀, 개별바비큐, 침대, 에어컨, TV, 취사시설, 식탁, 냉장고, 전자레인지, 커피포트, 실내개별온수수영장,헤어드라이기 샴푸린스바디워시비누치약 수건
2018년 3월 1일에 오픈한 독채형 신축풀빌라 객실입니다.
실내 개별 온수 수영장을 갖추고 있어 프라이빗한 휴식을 즐기실 수 있습니다.
야외개별데크에서 숯불로 이용하실 경우 2~4인 20,000원 / 4인초과시 30,000원입니다.
입실 후 현장에서 결제부탁드립니다.
* 여우의정원에 대해 더 상세한 내용을 확인해보세요', '온수는 31도, 온수요금은 별도로 받지 않고 객실요금에 포함입니다.
예약 인원에서 인원이 추가되는 경우 펜션에 미리 연락을 주시기 바랍니다.
최대 인원 초과 시 입실이 불가능할 수 있으며, 해당 사유로 환불 받을 수 없습니다.
반려동물 동반시 입실이 거부되며, 해당 사유로 환불 받을수 없습니다.
미성년자는 숙박이 불가능합니다.
다음 이용 고객을 위해 입실, 퇴실 시간을 준수해 주시기 바랍니다.
객실 및 주변시설 이용 시 시설물의 훼손, 분실에 대한 책임은 투숙객에게 있으며, 손해배상의 책임을 질 수 있습니다.
객실의 안전과 화재예방을 위해 객실 내에서 생선이나 고기 등을 굽는 직화 방식은 허용되지 않으며, 개인적으로 준비해 오는 취사도구(그릴, 숯, 전기/전열기구 등)은 반입이 금지되어 있습니다.
객실 내에서의 흡연은 금지되어 있으며, 지정된 장소를 이용해 주시기 바랍니다.
다른 이용객에게 피해를 줄 수 있는 무분별한 오락, 음주, 고성방가는 삼가주시기 바랍니다.', 4, '여우의정원', false, 2, 5);


-- 6번 호텔 방 정의
INSERT INTO ROOM
values (12, now(), now(), '최대 3박까지만 예약이 가능합니다.
＊개별욕실, 화장대, 테라스
＊이불 매트리스 추가가능
기준 인원 초과시, 현장에서 추가 요금이 발생합니다.
ㄴ인원 초과시 1인당 15,000원 현장지불 해야합니다.', '당일예약은 특성상 환불 및 취소가 불가합니다.
체크인은 23:00까지 가능합니다. 23:00 이후 입실이 불가하며 이 경우, 취소/환불은 불가합니다.
객실요금은 기준인원에 따라 다르며, 파티룸 등 특수객실의 경우, 직접 입실 인원 확인이 필요합니다.
도미토리객실은 타인과 함께 이용하는 다인실입니다. 결제시, 1인 1개 베드가 예약됩니다.
미성년자의 입실 가능여부는 직접 제휴점에 확인 후 예약 진행하시기 바랍니다.
미성년자 혼숙예약으로 인해 발생하는 입실 거부에 대해서는 취소/환불이 불가합니다.
제휴점 사정에 의한 취소 발생 시 100% 환불 처리됩니다.
제휴점 사정으로 객실 정보가 수시로 변경될 수 있습니다. 이로 인한 불이익은 당사가 책임지지 않습니다.', 6, '테라스 복층 룸', false, 2, 6);

INSERT INTO ROOM
values (13, now(), now(), '※ 파티 참석 희망하시는 분만 예약 가능합니다. (연박 이용시 연박 모두 파티 참석 하셔야 됩니다.)
파티신청은 당일 문자 안내드리며, 파티 참석하지 못하시는 경우 추가금액(5,000원) 발생하니 예약전 확인 부탁드립니다.
최대 3박까지만 예약이 가능합니다.
도미토리 객실은 결제시 1인 베드만 예약됩니다!
2인이상 투숙시 인원수에 맞게 여러번 예약 부탁드리겠습니다.
인원수에 맞지 않는 결제가 이루어질시 투숙이 불가한 점 양해부탁드리겠습니다.', '당일예약은 특성상 환불 및 취소가 불가합니다.
체크인은 23:00까지 가능합니다. 23:00 이후 입실이 불가하며 이 경우, 취소/환불은 불가합니다.
객실요금은 기준인원에 따라 다르며, 파티룸 등 특수객실의 경우, 직접 입실 인원 확인이 필요합니다.
도미토리객실은 타인과 함께 이용하는 다인실입니다. 결제시, 1인 1개 베드가 예약됩니다.
미성년자의 입실 가능여부는 직접 제휴점에 확인 후 예약 진행하시기 바랍니다.
미성년자 혼숙예약으로 인해 발생하는 입실 거부에 대해서는 취소/환불이 불가합니다.
제휴점 사정에 의한 취소 발생 시 100% 환불 처리됩니다.
제휴점 사정으로 객실 정보가 수시로 변경될 수 있습니다. 이로 인한 불이익은 당사가 책임지지 않습니다.', 6, '남성 도미토리 6-7인실', false, 1, 6);


/**
-- room image
 */
INSERT INTO ROOM_IMAGE
VALUES (1, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%841.png', 1);
INSERT INTO ROOM_IMAGE
VALUES (2, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%842.png', 1);

INSERT INTO ROOM_IMAGE
VALUES (3, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%8410.jpg', 2);

INSERT INTO ROOM_IMAGE
VALUES (4, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%849.jpg', 3);
INSERT INTO ROOM_IMAGE
VALUES (5, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%848.png', 3);
INSERT INTO ROOM_IMAGE
VALUES (6, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%845.jpg', 3);

INSERT INTO ROOM_IMAGE
VALUES (7, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%848.png', 4);

INSERT INTO ROOM_IMAGE
VALUES (8, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%847.png', 5);

INSERT INTO ROOM_IMAGE
VALUES (9, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%97%AC%EA%B4%80%EC%82%AC%EC%A7%841.jpg', 6);

INSERT INTO ROOM_IMAGE
VALUES (10, now(), now(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%843.png', 7);

INSERT INTO ROOM_IMAGE
VALUES (11, now(), now(),
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%9A%B0%EB%8A%94+%EA%B3%A0%EC%96%91%EC%9D%B4.png', 8);

INSERT INTO ROOM_IMAGE
VALUES (12, now(), now(),
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%9A%B0%EB%8A%94+%EA%B3%A0%EC%96%91%EC%9D%B4.png', 9);

INSERT INTO ROOM_IMAGE
VALUES (13, now(), now(),
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%9A%B0%EB%8A%94+%EA%B3%A0%EC%96%91%EC%9D%B4.png', 10);


INSERT INTO ROOM_IMAGE
VALUES (14, now(), now(),
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%9A%B0%EB%8A%94+%EA%B3%A0%EC%96%91%EC%9D%B4.png', 11);


INSERT INTO ROOM_IMAGE
VALUES (15, now(), now(),
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%9A%B0%EB%8A%94+%EA%B3%A0%EC%96%91%EC%9D%B4.png', 12);


INSERT INTO ROOM_IMAGE
VALUES (16, now(), now(),
        'https://zerobnb-bucket.s3.amazonaws.com/image/%EC%9A%B0%EB%8A%94+%EA%B3%A0%EC%96%91%EC%9D%B4.png', 13);

/**
-- rent_type,  lodgment room 정의
 */
-- 1번 방 정의
insert into rent_type_room
values (1, now(), now(), 25000, 10, 15, 1);
insert into lodgment_type_room
values (2, now(), now(), 35000, 10, 7, 1);

-- 2번 방 정의
insert into rent_type_room
values (3, now(), now(), 25000, 0, 5, 2);
insert into lodgment_type_room
values (4, now(), now(), 35000, 0, 5, 2);

-- 3번 방 정의
insert into rent_type_room
values (5, now(), now(), 25000, 30, 23, 3);
insert into lodgment_type_room
values (6, now(), now(), 35000, 30, 14, 3);


-- 4번 방 정의
insert into rent_type_room
values (7, now(), now(), 30000, 0, 15, 4);
insert into lodgment_type_room
values (8, now(), now(), 50000, 0, 15, 4);


-- 5번 방 정의
insert into rent_type_room
values (9, now(), now(), 35000, 0, 30, 5);
insert into lodgment_type_room
values (10, now(), now(), 50000, 0, 30, 5);


-- 6번 방 정의
insert into rent_type_room
values (11, now(), now(), 20000, 0, 15, 6);
insert into lodgment_type_room
values (12, now(), now(), 35000, 0, 15, 6);


-- 7번 방 정의
insert into rent_type_room
values (13, now(), now(), 50000, 0, 25, 7);
insert into lodgment_type_room
values (14, now(), now(), 80000, 0, 25, 7);


-- 8번 방 정의
insert into rent_type_room
values (15, now(), now(), 100000, 0, 7, 8);
insert into lodgment_type_room
values (16, now(), now(), 150000, 0, 7, 8);


-- 9번 방 정의
insert into rent_type_room
values (17, now(), now(), 150000, 0, 10, 9);
insert into lodgment_type_room
values (18, now(), now(), 200000, 0, 10, 9);


-- 10번 방 정의
insert into rent_type_room
values (19, now(), now(), 15000, 0, 10, 10);
insert into lodgment_type_room
values (20, now(), now(), 25000, 0, 20, 10);


-- 11번 방 정의
insert into rent_type_room
values (21, now(), now(), 20000, 0, 10, 11);
insert into lodgment_type_room
values (22, now(), now(), 40000, 0, 10, 11);


-- 12번 방 정의
insert into rent_type_room
values (23, now(), now(), 20000, 0, 13, 12);
insert into lodgment_type_room
values (24, now(), now(), 30000, 0, 6, 12);


-- 13번 방 정의
insert into rent_type_room
values (25, now(), now(), 0, 0, 0, 13);
insert into lodgment_type_room
values (26, now(), now(), 50000, 0, 15, 13);


/**
-- accommodation image
 */
INSERT INTO accommodation_image
values (1, NOW(), NOW(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%841.png', 1);
INSERT INTO accommodation_image
values (2, NOW(), NOW(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%8410.jpg', 2);
INSERT INTO accommodation_image
values (3, NOW(), NOW(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%846.png', 3);
INSERT INTO accommodation_image
values (4, NOW(), NOW(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%843.png', 3);
INSERT INTO accommodation_image
values (5, NOW(), NOW(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%842.png', 4);
INSERT INTO accommodation_image
values (6, NOW(), NOW(), 'https://zerobnb-bucket.s3.amazonaws.com/image/%ED%98%B8%ED%85%94%EC%82%AC%EC%A7%848.png', 5);
INSERT INTO accommodation_image
values (7, NOW(), NOW(), '<<undefined>>', 6);


/**
-- popular facility service
 */
-- 호텔1의 옵션
INSERT INTO popular_facility_service
values (1, NOW(), NOW(), 1, 1);

INSERT INTO popular_facility_service
values (2, NOW(), NOW(), 2, 1);

INSERT INTO popular_facility_service
values (3, NOW(), NOW(), 4, 1);

INSERT INTO popular_facility_service
values (4, NOW(), NOW(), 5, 1);

INSERT INTO popular_facility_service
values (5, NOW(), NOW(), 6, 1);

INSERT INTO popular_facility_service
values (6, NOW(), NOW(), 7, 1);

INSERT INTO popular_facility_service
values (7, NOW(), NOW(), 8, 1);

INSERT INTO popular_facility_service
values (8, NOW(), NOW(), 9, 1);

-- 호텔 2의 옵션
INSERT INTO popular_facility_service
values (9, NOW(), NOW(), 1, 2);

INSERT INTO popular_facility_service
values (10, NOW(), NOW(), 2, 2);

INSERT INTO popular_facility_service
values (11, NOW(), NOW(), 4, 2);

INSERT INTO popular_facility_service
values (12, NOW(), NOW(), 5, 2);

INSERT INTO popular_facility_service
values (13, NOW(), NOW(), 6, 2);

INSERT INTO popular_facility_service
values (14, NOW(), NOW(), 7, 2);

INSERT INTO popular_facility_service
values (15, NOW(), NOW(), 8, 2);

INSERT INTO popular_facility_service
values (16, NOW(), NOW(), 13, 2);

-- 호텔 3의 옵션
INSERT INTO popular_facility_service
values (17, NOW(), NOW(), 1, 3);

INSERT INTO popular_facility_service
values (18, NOW(), NOW(), 2, 3);

INSERT INTO popular_facility_service
values (19, NOW(), NOW(), 3, 3);

INSERT INTO popular_facility_service
values (20, NOW(), NOW(), 4, 3);

INSERT INTO popular_facility_service
values (21, NOW(), NOW(), 5, 3);

INSERT INTO popular_facility_service
values (22, NOW(), NOW(), 6, 3);

INSERT INTO popular_facility_service
values (23, NOW(), NOW(), 7, 3);

-- 호텔 4의 옵션
INSERT INTO popular_facility_service
values (24, NOW(), NOW(), 2, 4);

INSERT INTO popular_facility_service
values (25, NOW(), NOW(), 5, 4);

INSERT INTO popular_facility_service
values (26, NOW(), NOW(), 6, 4);

INSERT INTO popular_facility_service
values (27, NOW(), NOW(), 9, 4);

INSERT INTO popular_facility_service
values (28, NOW(), NOW(), 10, 4);

INSERT INTO popular_facility_service
values (29, NOW(), NOW(), 13, 4);


-- 호텔 5의 옵션
INSERT INTO popular_facility_service
values (30, NOW(), NOW(), 4, 5);

INSERT INTO popular_facility_service
values (31, NOW(), NOW(), 8, 5);

INSERT INTO popular_facility_service
values (32, NOW(), NOW(), 9, 5);

INSERT INTO popular_facility_service
values (33, NOW(), NOW(), 11, 5);

INSERT INTO popular_facility_service
values (34, NOW(), NOW(), 12, 5);

INSERT INTO popular_facility_service
values (35, NOW(), NOW(), 13, 5);

INSERT INTO popular_facility_service
values (36, NOW(), NOW(), 14, 5);


-- 호텔 6의 옵션
INSERT INTO popular_facility_service
values (37, NOW(), NOW(), 1, 5);

INSERT INTO popular_facility_service
values (38, NOW(), NOW(), 2, 5);

INSERT INTO popular_facility_service
values (39, NOW(), NOW(), 3, 6);

INSERT INTO popular_facility_service
values (40, NOW(), NOW(), 4, 6);

INSERT INTO popular_facility_service
values (41, NOW(), NOW(), 5, 6);

INSERT INTO popular_facility_service
values (42, NOW(), NOW(), 8, 6);

INSERT INTO popular_facility_service
values (43, NOW(), NOW(), 9, 6);

INSERT INTO popular_facility_service
values (44, NOW(), NOW(), 10, 6);

INSERT INTO popular_facility_service
values (45, NOW(), NOW(), 11, 6);

INSERT INTO popular_facility_service
values (46, NOW(), NOW(), 12, 6);

INSERT INTO popular_facility_service
values (47, NOW(), NOW(), 13, 6);


/**
  -- event 정의
**/
-- 1번 호텔
INSERT INTO event
values (1, now(), now(), '과자,조식 무료 제공합니다.', 1);
INSERT INTO event
values (2, now(), now(), '첫방문 손님께 50% 할인 합니다.', 1);

-- 2번 호텔
INSERT INTO event
values (3, now(), now(), '성인용품 마음껏 쓰세요~!', 2);
INSERT INTO event
values (4, now(), now(), '넷플리스 무료 시청', 2);
INSERT INTO event
values (5, now(), now(), '시원한 제철과일 마음껏 취식', 2);

-- 3번 호텔
INSERT INTO event
values (6, now(), now(), '과자, 음료 무료 제공', 3);
INSERT INTO event
values (7, now(), now(), '최고사양 컴퓨터 제공', 3);
INSERT INTO event
values (8, now(), now(), '시원한 얼음찜질 마사지 제공', 3);


-- 4번 호텔
INSERT INTO event
values (9, now(), now(), '영화 무료 시청', 4);;


-- 5번 호텔
INSERT INTO event
values (10, now(), now(), '베틀그라운드 최고사양 컴퓨터 제공', 5);
INSERT INTO event
values (11, now(), now(), '무료 비디오 시청', 5);


-- 6번 호텔
INSERT INTO event
values (12, now(), now(), '선착순 30명에게 숙박 40% 할인', 3);


/**
-- 쿠폰 정의
 */
INSERT INTO COUPON
values (1, now(), now(), 10, 'rent', 30, '선착순 30% 대실 할인 쿠폰!', 1);
INSERT INTO COUPON
values (2, now(), now(), 10, 'lodgment', 10, '선착순 10% 숙박 할인 쿠폰!', 1);

INSERT INTO COUPON
values (3, now(), now(), 30, 'all', 50, '선착순 50% 숙박/대실 할인 쿠폰!', 3);

INSERT INTO COUPON
values (4, now(), now(), 50, 'rent', 25, '25% 대실 할인 쿠폰입니다.', 4);
INSERT INTO COUPON
values (5, now(), now(), 50, 'lodgment', 45, '45% 숙박 할인 쿠폰 입니다.', 4);


INSERT INTO COUPON
values (6, now(), now(), 30, 'rent', 20, '20% 대실 할인 쿠폰', 5);
INSERT INTO COUPON
values (7, now(), now(), 30, 'lodgment', 20, '20% 숙박 할인 쿠폰', 5);
INSERT INTO COUPON
values (8, now(), now(), 30, 'all', 20, '20% 숙박/대실 할인 쿠폰', 5);


/**
-- 리뷰
 */
-- 1번 게스트
insert into review
values (1, now(), now(),
        '저녁 식사 늦게라도 참석하고 싶다는 의사 밣히고 해당 업체에서도 늦게라도 오라고 했지만 막상가니 이 핑계 저 핑계대면서 방으로 들어가라는 식. 다시는 안갈거 같음. 서비스 정신결여.', 1, 1);

insert into review_category
values (1, 1, 1, 1);
insert into review_category
values (2, 2, 3, 1);
insert into review_category
values (3, 3, 3, 1);
insert into review_category
values (4, 4, 5, 1);

insert into review
values (2, now(), now(),
        '서울 중심인 광화문에 있는 호텔이라 여기저기 구경할 곳은 많아서 좋았습니다 먹을 곳도 많았구요 ㅎㅎ 살짝 아쉬운 점은 리뉴얼 했다지만 그래도 느껴지는 고전적인 카페트가 좀 아쉬웠던 거 같습니다 그래도 친절하고 다른 점들도 좋아서 재방문하고 싶어요',
        1, 5);

insert into review_category
values (5, 1, 3, 2);
insert into review_category
values (6, 2, 5, 2);
insert into review_category
values (7, 3, 5, 2);
insert into review_category
values (8, 4, 5, 2);

insert into review
values (3, now(), now(),
        '한마디만 할게요. 존나 구려요. 두번 다신 안옴 ㅅㄱ', 1, 8);

insert into review_category
values (9, 1, 1, 3);
insert into review_category
values (10, 2, 1, 3);
insert into review_category
values (11, 3, 1, 3);
insert into review_category
values (12, 4, 1, 3);


-- 2번 게스트
insert into review
values (4, now(), now(),
        '최고에요~', 2, 8);

insert into review_category
values (13, 1, 5, 4);
insert into review_category
values (14, 2, 5, 4);
insert into review_category
values (15, 3, 5, 4);
insert into review_category
values (16, 4, 5, 4);

insert into review
values (5, now(), now(),
        '서울 중심인 광화문에 있는 호텔이라 여기저기 구경할 곳은 많아서 좋았습니다 먹을 곳도 많았구요 ㅎㅎ 살짝 아쉬운 점은 리뉴얼 했다지만 그래도 느껴지는 고전적인 카페트가 좀 아쉬웠던 거 같습니다 그래도 친절하고 다른 점들도 좋아서 재방문하고 싶어요',
        2, 7);

insert into review_category
values (17, 1, 5, 5);
insert into review_category
values (18, 2, 5, 5);
insert into review_category
values (19, 3, 5, 5);
insert into review_category
values (20, 4, 5, 5);

insert into review
values (6, now(), now(),
        '일단 화장실이 진짜 노후됐고 겉에만 리모델링 잘해놨네요
헬스장은 일욜에 휴관이라 이용도 못했고 토욜은 7시에 닫아서 구경도 못했어요
이용시간을 고지해주셨으면 여기호텔까지 굳이 안왔을것같아요
티비도 90년대에 산것처럼 옛날티비라 화질도 안좋고 창문도 없어서 환풍구 열어서 환기했어요', 2, 4);

insert into review_category
values (21, 1, 2, 6);
insert into review_category
values (22, 2, 2, 6);
insert into review_category
values (23, 3, 3, 6);
insert into review_category
values (24, 4, 2, 6);

insert into review
values (7, now(), now(),
        '잘 이용하다 갑니다~', 2, 2);

insert into review_category
values (25, 1, 5, 7);
insert into review_category
values (26, 2, 5, 7);
insert into review_category
values (27, 3, 5, 7);
insert into review_category
values (28, 4, 5, 7);

insert into review
values (8, now(), now(),
        '뷰티패키지가 별거 아니네요ㅠ
온열안대말고 써볼만한게 없어요
', 2, 10);

insert into review_category
values (29, 1, 2, 8);
insert into review_category
values (30, 2, 2, 8);
insert into review_category
values (31, 3, 2, 8);
insert into review_category
values (32, 4, 2, 8);

-- 3번 고객
insert into review
values (9, now(), now(),
        '그저 그랬어요.', 3, 6);

insert into review_category
values (33, 1, 3, 9);
insert into review_category
values (34, 2, 3, 9);
insert into review_category
values (35, 3, 3, 9);
insert into review_category
values (36, 4, 3, 9);

-- 4번 고객
insert into review
values (10, now(), now(),
        '화장실이 깨끗해서 좋았어요', 4, 4);

insert into review_category
values (37, 1, 5, 10);
insert into review_category
values (38, 2, 5, 10);
insert into review_category
values (39, 3, 5, 10);
insert into review_category
values (40, 4, 5, 10);


-- 5번 고객
insert into review
values (11, now(), now(),
        '동료들과 좋은시간 보내다 갑니다~ 장사 잘하셔요', 5, 13);

insert into review_category
values (41, 1, 5, 11);
insert into review_category
values (42, 2, 5, 11);
insert into review_category
values (43, 3, 5, 11);
insert into review_category
values (44, 4, 5, 11);

-- 6번 고객
insert into review
values (12, now(), now(),
        '객실이란 상품입니다. 제가 묵은 방에 한해서 단정지어 말할 수 있습니다. ''이 모텔은 상품 가치가 전혀 없는 객실을 아무렇지 않게 방문자에게 제공한다'' 라고...엄청난 먼지와 걸레 수준의 가운, 낡아 제대로 기능하지 않고 덜덜거리는 에어컨, 전혀 차단되지 않는 알 수 없는 기계의 웅웅거림 등... 어디에서도 고객 편의 제공을 위한 노력을 찾아볼 수 없습니다. 재방문 의사 없습니다.',
        6, 1);

insert into review_category
values (45, 1, 1, 12);
insert into review_category
values (46, 2, 1, 12);
insert into review_category
values (47, 3, 1, 12);
insert into review_category
values (48, 4, 1, 12);

insert into review
values (13, now(), now(),
        '방음이전혀되지않아 방에들어가기전부터 다른방에서 엄청난 소리에 흥분이 가시질않았습니다
', 6, 3);

insert into review_category
values (49, 1, 1, 13);
insert into review_category
values (50, 2, 1, 13);
insert into review_category
values (51, 3, 1, 13);
insert into review_category
values (52, 4, 1, 13);


insert into review
values (14, now(), now(),
        '침대도 뜨근하고 방안에 세스코 있어서 향기롭고 청결하네요', 6, 5);

insert into review_category
values (53, 1, 5, 14);
insert into review_category
values (54, 2, 5, 14);
insert into review_category
values (55, 3, 5, 14);
insert into review_category
values (56, 4, 5, 14);

-- 7번 고객
insert into review
values (15, now(), now(),
        '완전 깨끗하고 방도 좋고 시설이 너무 좋아요', 7, 8);

insert into review_category
values (57, 1, 5, 15);
insert into review_category
values (58, 2, 5, 15);
insert into review_category
values (59, 3, 5, 15);
insert into review_category
values (60, 4, 5, 15);

-- 10번 고객
insert into review
values (16, now(), now(),
        '굿둣구수수구구', 10, 8);

insert into review_category
values (61, 1, 5, 16);
insert into review_category
values (62, 2, 5, 16);
insert into review_category
values (63, 3, 5, 16);
insert into review_category
values (64, 4, 5, 16);


/**
-- 예약 및 청구서 정의
 */
-- 1번 고객
INSERT INTO reservation
values (1, now(), now(), '강영민', '010-5948-3938', '2020-04-29 20:00:00', '2022-04-30 15:00:00', 500000, 1, 'foot', 1,
        10);
INSERT INTO payment
values (1, now(), now(), 0, 1, 0, 500000, 1);

INSERT INTO reservation
values (2, now(), now(), '강영민', '010-5948-3938', '2021-11-01 19:00:00', '2021-11-01 22:00:00', 30000, 0, 'foot', 1,
        5);
INSERT INTO payment
values (2, now(), now(), 0, 1, 0, 30000, 2);

INSERT INTO reservation
values (3, now(), now(), '강영민', '010-5948-3938', '2022-04-05 13:00:00', '2022-04-05 17:00:00', 200000, 1, 'foot', 1,
        7);
INSERT INTO payment
values (3, now(), now(), 0, 1, 0, 200000, 3);


INSERT INTO reservation
values (4, now(), now(), '강영민', '010-5948-3938', '2019-04-05 13:00:00', '2019-04-05 17:00:00', 200000, 1, 'foot', 1,
        7);
INSERT INTO payment
values (4, now(), now(), 0, 1, 0, 200000, 4);


-- 2번 고객
INSERT INTO reservation
values (5, now(), now(), '김구라', '010-4343-5435', '2022-04-08 13:00:00', '2022-04-09 16:00:00', 20000, 1, 'foot', 2,
        7);
INSERT INTO payment
values (5, now(), now(), 0, 1, 0, 20000, 5);

INSERT INTO reservation
values (6, now(), now(), '김구라', '010-4343-5435', '2022-04-08 13:00:00', '2022-04-09 16:00:00', 20000, 1, 'foot', 2,
        7);
INSERT INTO payment
values (6, now(), now(), 0, 1, 0, 20000, 6);

INSERT INTO reservation
values (7, now(), now(), '김구라', '010-4343-5435', '2022-04-08 13:00:00', '2022-04-09 16:00:00', 20000, 1, 'foot', 2,
        11);
INSERT INTO payment
values (7, now(), now(), 0, 1, 0, 20000, 7);

INSERT INTO reservation
values (8, now(), now(), '김구라', '010-4343-5435', '2022-04-08 13:00:00', '2022-04-09 16:00:00', 20000, 1, 'foot', 2,
        8);
INSERT INTO payment
values (8, now(), now(), 0, 1, 0, 20000, 8);

INSERT INTO reservation
values (9, now(), now(), '김구라', '010-4343-5435', '2022-04-08 13:00:00', '2022-04-09 16:00:00', 20000, 1, 'foot', 2,
        4);
INSERT INTO payment
values (9, now(), now(), 0, 1, 0, 20000, 9);

INSERT INTO reservation
values (10, now(), now(), '김구라', '010-4343-5435', '2022-04-08 13:00:00', '2022-04-09 16:00:00', 20000, 1, 'foot', 2,
        7);
INSERT INTO payment
values (10, now(), now(), 0, 1, 0, 20000, 10);

-- 3번 고객
INSERT INTO reservation
values (11, now(), now(), '김주민', '010-9828-3333', '2021-03-28 19:00:00', '2021-03-28 22:00:00', 25000, 0, 'vehicle', 3,
        6);
INSERT INTO payment
values (11, now(), now(), 0, 1, 0, 20000, 11);

-- 4번 고객
INSERT INTO reservation
values (12, now(), now(), '여주영', '010-2185-3892', '2022-01-01 19:00:00', '2022-01-02 13:00:00', 150000, 1, 'foot', 4,
        11);
INSERT INTO payment
values (12, now(), now(), 0, 1, 0, 20000, 12);

-- 5번 고객
INSERT INTO reservation
values (13, now(), now(), '정주영', '010-3948-3567', '2022-04-04 19:00:00', '2022-04-06 13:00:00', 390000, 2, 'foot', 5,
        11);
INSERT INTO payment
values (13, now(), now(), 0, 1, 0, 390000, 13);


-- 6번 고객
INSERT INTO reservation
values (14, now(), now(), '신지연', '010-4343-3323', '2022-04-04 19:00:00', '2022-04-06 13:00:00', 390000, 2, 'foot', 6,
        3);
INSERT INTO payment
values (14, now(), now(), 0, 1, 0, 390000, 14);

INSERT INTO reservation
values (15, now(), now(), '신지연', '010-4343-3323', '2021-04-04 19:00:00', '2021-04-06 13:00:00', 390000, 2, 'foot', 6,
        7);
INSERT INTO payment
values (15, now(), now(), 0, 1, 0, 390000, 15);

INSERT INTO reservation
values (16, now(), now(), '신지연', '010-4343-3323', '2020-04-04 19:00:00', '2020-04-06 13:00:00', 390000, 2, 'foot', 6,
        8);
INSERT INTO payment
values (16, now(), now(), 0, 1, 0, 390000, 16);

-- 7번 고객
INSERT INTO reservation
values (17, now(), now(), '이유진', '010-1577-1577', '2022-07-01 19:00:00', '2022-07-01 13:00:00', 30000, 1, 'foot', 7,
        12);
INSERT INTO payment
values (17, now(), now(), 0, 1, 0, 30000, 17);

INSERT INTO reservation
values (18, now(), now(), '이유진', '010-1577-1577', '2022-06-26 19:00:00', '2022-06-27 13:00:00', 30000, 1, 'foot', 7,
        12);
INSERT INTO payment
values (18, now(), now(), 0, 1, 0, 30000, 18);

-- 10번 고객
INSERT INTO reservation
values (19, now(), now(), '김준호', '010-4664-5453', '2022-05-25 19:00:00', '2022-05-25 22:00:00', 27000, 0, 'foot', 7,
        11);
INSERT INTO payment
values (19, now(), now(), 0, 1, 0, 30000, 19);
