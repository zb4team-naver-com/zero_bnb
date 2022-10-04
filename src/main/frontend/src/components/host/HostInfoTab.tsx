import styled from "styled-components"
import getInfo from "../../services/HostAPI/host_axios"
import { useNavigate } from "react-router-dom"
import Button from "../common/Button"
import Table from "../common/Table"
import { initialValue } from "../../services/hostRegister"

export default function HostInfoTab() {
	const nav = useNavigate()
	const { data } = getInfo("saymymin@kakao.com")
	//

	const init = initialValue
	const renderValue = data
		? {
				profileImage: data.profileImage,
				name: "userName",
				businessContact: data.businessContact,
				companyRegistrationNumber: data.companyRegistrationNumber,
				email: "saymymin@kakao.com",
		  }
		: init

	const style = {
		width: 6 + "rem",
		height: 4 + "rem",
		marginTop: 3 + "rem",
	}

	const props = {
		headList: ["호스트명", "연락처", "사업자 등록 번호", "이메일"],
		desList: Object.values(renderValue).slice(1),
	}

	return (
		<>
			<S.Div>
				<S.Img src={renderValue.profileImage} alt="호스트 이미지"></S.Img>
				<S.Info>
					<S.Table>
						<Table {...props} />
						<Button
							type="button"
							text="수정"
							style={style}
							onClick={() => nav("/host/update")}
						/>
					</S.Table>
				</S.Info>
			</S.Div>
		</>
	)
}

const S: any = {}

S.Div = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 102rem;
	margin: 0 auto;
	margin-top: 10rem;
`

S.Info = styled.div`
	width: 45rem;
	margin-top: 5rem;
	border-top: 1px solid var(--main-color2);
`

S.Table = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 3rem;
	margin-left: 3rem;
`

S.Img = styled.img`
	width: 13rem;
	height: 13rem;
	background: var(--color-gray1);
	border-radius: 50%;
	border: 2px solid var(--color-gray2);
	cursor: pointer;
`
