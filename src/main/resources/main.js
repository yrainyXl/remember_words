document.addEventListener('DOMContentLoaded', () => {
    const wordInput = document.getElementById('wordInput');
    const submitButton = document.getElementById('submitButton');
    const message = document.getElementById('message'); // 获取提示信息元素
  
    // 显示提示信息的方法
    const showMessage = (text, type = 'success') => {
        console.log('Message element:', message);
      message.textContent = text; // 设置消息内容
      message.style.color = type === 'success' ? '#22c55e' : '#e36a6a'; // 成功显示绿色，失败显示红色
    //   message.style.display = 'block';
      message.classList.add('show'); // 添加显示类
  
      // 3秒后自动隐藏
      setTimeout(() => {
        message.classList.remove('show'); // 移除显示类
      }, 3000);
    };
  
    // 提交单词逻辑
    submitButton.addEventListener('click', async () => {
      const word = wordInput.value.trim();
      if (!word) {
        showMessage('请输入一个单词！', 'error');
        return;
      }
  
      // 调用你的接口保存单词
      try {
        const controller = new AbortController(); // 创建 AbortController 用于控制超时
        const timeoutId = setTimeout(() => controller.abort(), 5000); // 5 秒超时
  
        const response = await fetch(`http://yrainy.ac.cn:8090/words?word=${encodeURIComponent(word)}`, {
          method: 'POST',
          signal: controller.signal, // 设置超时信号
        });
  
        clearTimeout(timeoutId); // 清除超时计时器
  
        
        if (response.ok) {
            const result = await response.json(); // 解析后端的 JSON 响应
            if (result.status === 0) {
              // 后端返回的 status 为 0 表示成功
              showMessage(`单词 "${word}" 保存成功！`);
              wordInput.value = ''; // 清空输入框
            } else {
              // 后端返回的 status 非 0 表示业务失败
              showMessage(`保存失败：${result.msg || '未知错误'}`, 'error');
            }
          } else {
            // HTTP 错误处理
            showMessage('保存失败：服务器错误，请稍后重试！', 'error');
          }
      } catch (error) {
        if (error.name === 'AbortError') {
          showMessage('保存失败，请求超时！', 'error');
        } else {
          showMessage('保存失败，网络错误！', 'error');
        }
      }
    });
  });
  