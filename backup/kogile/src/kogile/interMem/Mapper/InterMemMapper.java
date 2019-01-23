package kogile.interMem.Mapper;

import kogile.module.InterMember;
import kogile.module.IsInterMem;
import kogile.module.TotalMemInfo;

import org.apache.ibatis.annotations.Select;

import kogile.interMem.Mapper.InterMemMapper;

public interface InterMemMapper {
	
	int insertInterMember(InterMember member);
	InterMember selectInterMember(String email);
	int selectTotalMemNumWithInMem(int member_no);
	//TotalMemInfo selectMemInfoWithTotalMemNum(int total_m_no);
	
	//���� ����� ����ȸ������ �ܺ�ȸ������ �Ǵ� �� �� �ִ�.
	@Select("select exter_m_no, member_no from TOTAL_MEMBER where total_m_no = #{total_m_no}")
	IsInterMem IsInterMember(int total_m_no);
	
	//��ȸ�� �ܺ�ȸ����ȣ�� �ܺ�ȸ�� ���� Ȯ��.
	@Select("select exter_mem_name as member_name, exter_m_email as member_email from EXTERNAL_M_INFO where exter_m_no = #{exter_m_no}")
	TotalMemInfo exterMemSearch(int exter_m_no);
	
	@Select("select member_name, email as member_email from INTER_M_INFO where member_no = #{member_no}")
	TotalMemInfo interMemSearch(int member_no);

}
