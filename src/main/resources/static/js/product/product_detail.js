document.addEventListener('DOMContentLoaded', function () {
    // 장바구니 담기 버튼 클릭 이벤트
    const addToCartButton = document.getElementById('addToCart');

    if (addToCartButton) {
        addToCartButton.addEventListener('click', function () {
            // 상품 정보를 JSON 객체로 구성
            const stock = 1; // 예시로 수량은 1로 설정

            const cartItem = {
                stock: stock
            };

            // 현재 페이지의 URL 가져오기
            const currentUrl = window.location.href;
            const token = localStorage.getItem('jwt');

            // fetch를 사용하여 PUT 요청 보내기
            fetch(currentUrl, {
                method: 'PUT',
                headers: {
                    'Authorization': token ? `Token ${token}` : '',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cartItem) // JSON으로 변환하여 보냄
            })
                .then(response => {
                if (response.ok) {
                    alert('장바구니에 상품이 담겼습니다!');
                }
            })
        });
    }
});
