document.addEventListener('DOMContentLoaded', function() {
    const productForm = document.getElementById('productForm');

    productForm.addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 방지

        const productData = {
            name: document.getElementById('name').value.trim(),
            description: document.getElementById('description').value.trim(),
            price: parseFloat(document.getElementById('price').value),
            stock: parseInt(document.getElementById('stock').value, 10),
            category: document.getElementById('category').value,
            imageUrl: document.getElementById('imageUrl').value.trim(),
            userId: 1 // 예시로 1번 사용자 (실제 로그인 유저 정보 받아야 함)
        };

        // 필수값 확인 (nullable 방지)
        if (!productData.name || !productData.description || isNaN(productData.price) || isNaN(productData.stock)) {
            alert('⚠ 모든 필수 입력값을 입력해주세요.');
            return;
        }

        fetch('/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productData)
        })
            .then(response => response.json())
            .then(data => {
            if (data.id) {
                alert('✅ 상품 등록 성공!');
                window.location.href = '/products'; // 상품 목록 페이지로 이동
            } else {
                alert('❌ 상품 등록 실패: ' + data.message);
            }
        })
            .catch(error => {
            console.error('Error:', error);
            alert('⚠ 서버 오류 발생');
        });
    });
});
