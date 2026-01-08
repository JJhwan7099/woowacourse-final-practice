package attendance.domain;

import attendance.constants.Error;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private final List<Member> members = new ArrayList<>();

    public void add(Member member) {
        members.add(member);
    }

    public Member getMemberByName(String name) {
        return members.stream()
                .filter(member -> member.getName().equals(name))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException(Error.memberNotFound)
                );
    }

    public List<Member> getExplusionMembers() {
        return members.stream()
                .filter(member -> !member.getMemberStatus().equals(null))
                .toList();
    }

    public boolean isAlreadyRegister(String name) {
        return members.stream()
                .anyMatch(member -> member.getName().equals(name));
    }
}
