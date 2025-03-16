document.addEventListener("DOMContentLoaded", function () {
    const cartButton = document.getElementById("cartButton");

    if (cartButton) {
        cartButton.addEventListener("click", function () {
            const token = localStorage.getItem("jwt"); // 예시: localStorage에서 토큰 가져오기

            if (!token) {
                alert("로그인이 필요합니다.");
                return;
            }

            fetch('/cart', {
                method: 'GET',
                headers: {
                    'Authorization': `Token ${token}`
                }
            })
                .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.text();
            })
                .then(html => {
                document.open();
                document.write(html);
                document.close();
            })
                .catch(error => console.error("Error fetching cart page:", error));
        });
    }
});
