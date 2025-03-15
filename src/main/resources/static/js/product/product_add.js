document.addEventListener('DOMContentLoaded', function() {
    const productForm = document.getElementById('productForm');

    productForm.addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 방지
        const fileInput = document.getElementById('image'); // 파일 입력 필드 가져오기
        const formData = new FormData();

        const productData = {
            name: document.getElementById('name').value.trim(),
            description: document.getElementById('description').value.trim(),
            price: parseFloat(document.getElementById('price').value),
            stock: parseInt(document.getElementById('stock').value, 10),
            category: document.getElementById('category').value,
        };

        formData.append("image", fileInput.files[0]); // 파일 추가
        formData.append("product", new Blob([JSON.stringify(productData)], { type: "application/json" }));

        // 필수값 확인 (nullable 방지)
        if (!productData.name || !productData.description || isNaN(productData.price) || isNaN(productData.stock)) {
            alert('⚠ 모든 필수 입력값을 입력해주세요.');
            return;
        }

        const token = localStorage.getItem('jwt');

        fetch('/products/product-add', {
            method: 'PUT',
            headers: {
                'Authorization': token ? `Token ${token}` : ''
            },
            body: formData
        })
            .then(response => response.json())
            .then(data => {
            console.log(data);
            if (data.product) {
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
