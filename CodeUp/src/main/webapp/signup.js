let isDuplicate = false;

function checkDuplicate() {
    const username = document.getElementById('username').value;
    const duplicateMessage = document.getElementById('duplicateMessage');

    if (!username) {
        duplicateMessage.textContent = "아이디를 입력하세요.";
        return;
    }

    fetch(`check-duplicate?username=${encodeURIComponent(username)}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                isDuplicate = true;
                duplicateMessage.textContent = "이미 존재하는 아이디입니다.";
            } else {
                isDuplicate = false;
                duplicateMessage.textContent = "사용 가능한 아이디입니다.";
            }
        })
        .catch(error => {
            duplicateMessage.textContent = "중복 확인 중 오류가 발생했습니다.";
            console.error(error);
        });
}

function validateForm() {
    const duplicateMessage = document.getElementById('duplicateMessage');
    const username = document.getElementById('username').value;

    if (!username) {
        duplicateMessage.textContent = "아이디를 입력하세요.";
        return false; // 폼 제출 방지
    }

    if (isDuplicate) {
        duplicateMessage.textContent = "이미 존재하는 아이디입니다. 다른 아이디를 입력하세요.";
        return false; // 폼 제출 방지
    }

    return true; // 중복 확인 통과 시 폼 제출 허용
}

