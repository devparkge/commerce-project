document.addEventListener('DOMContentLoaded', function() {
    const signupForm = document.querySelector('form');
    if (signupForm) {
        signupForm.addEventListener('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 방지

            const formData = new FormData(signupForm);
            const userData = {
                email: formData.get('email'),
                password: formData.get('password'),
                username: formData.get('username'),
                phoneNumber: formData.get('phoneNumber'),
                address: formData.get('address')
            };

            // nullable 방지: 필수 입력 항목이 비어있는지 체크
            if (!userData.email || !userData.password || !userData.username || !userData.phoneNumber || !userData.address) {
                alert("모든 내용을 작성해주세요.");
                return; // 폼 제출을 막음
            }

            // 회원가입 API 호출
            fetch('/users/signup', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            })
                .then(response => response.json())
                .then(data => {
                if (data.user != null) {
                    alert('회원가입 성공');
                    console.log(data.user.userId);
                    window.location.href = '/users/login'; // 회원가입 후 로그인 페이지로 이동
                } else {
                    alert('회원가입 실패: ' + data.message);
                }
            })
                .catch(error => {
                console.error('Error:', error);
                alert('서버 오류 발생');
            });
        });
    }
});
