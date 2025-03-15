document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 방지

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            // 로그인 요청 데이터
            const loginData = {
                email: email,
                password: password
            };

            // 로그인 API 호출
            fetch('/users/login', {
                method: 'POST', // 로그인 요청은 보통 POST 방식입니다.
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            })
                .then(response => {
                console.log(response);
                if (response.status === 200) {
                    return response.json(); // 성공적으로 로그인 된 경우
                } else if (response.status === 400) {
                    // 400 상태 코드일 때
                    alert('비밀번호가 일치하지 않습니다.')
                } else if (response.status === 404) {
                    alert('존재하지 않는 이메일 입니다.')
                }
            })
                .then(data => {
                // JWT 토큰을 로컬 스토리지에 저장
                localStorage.setItem('jwt', data.user.token);
                alert('로그인 성공');
                window.location.href = '/products'; // 로그인 성공 후 대시보드로 이동
            })
        });
    }
});
